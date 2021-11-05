import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.awt.image.BufferedImage;

//  the render manager which receives rendering calls and starts the rendering threads

class RenderManager {
  private BufferedImage image;
  private int imgWidth, imgHeight;
  private ImagePanel panel;
  private double centerX, centerY, zoomFact;
  private ForkJoinPool pool;

  RenderManager(ImagePanel p) {
    panel = p;
    image = p.getImage();
    imgWidth = p.getImgWidth();
    imgHeight = p.getImgHeight();
    pool = new ForkJoinPool();
  }

  boolean isWorking() {  return !pool.isQuiescent();  }

  //  begin rendering the view at (x, y) with zoom factor z
  void startRender(double x, double y, double z) {
    centerX = x;
    centerY = y;
    zoomFact = z;

    //  start an initial rendering task that covers the whole image
    pool.execute(new RenderTask(0, 0, imgWidth, imgHeight));
  }


  //  the class representing a rendering task which will recursively divide
  //  into smaller tasks, and directly render a task when small enough
  private class RenderTask extends RecursiveAction {

    private int xStart, yStart, width, height;

    RenderTask(int x, int y, int w, int h) {
      xStart = x;
      yStart = y;
      width = w;
      height = h;
    }

    //  the entry-point and logic for the recursive renderer
    public void compute() {
      if (width*height > Main.THRESHOLD)  recurse();
      else                                render();
    }

    //  if the task is too large then it is divided in half along the larger axis
    private void recurse() {
      if (width > height)  invokeAll(new RenderTask(xStart, yStart, width/2, height),
                                     new RenderTask(xStart+width/2, yStart, width-width/2, height));

      else                 invokeAll(new RenderTask(xStart, yStart, width, height/2),
                                     new RenderTask(xStart, yStart+height/2, width, height-height/2));
    }

    //  the rendering method that eventually renders and finishes each task
    private void render() {

      for (int y = yStart; y < yStart+height; ++y) {
        for (int x = xStart; x < xStart+width; ++x) {

          //  for each pixel, compute its location Z in the complex plane
          Complex z0 = new Complex(centerX+(x-imgWidth/2)*zoomFact/imgWidth, centerY+(y-imgHeight/2)*zoomFact/imgWidth);
          Complex z = new Complex(z0);

          //  then iterate Z' = Z²+Z₀
          int i = 0;
          do  z = z.sqr().plus(z0);  while (++i < Main.ITER  &&  z.mag() < 4);

          //  if the point is still within 4 units from origo then we
          //  consider it to belong to the mandelbrot set
          int ic = (i-Main.ITER/2);
          int c = i == Main.ITER ? 0x402020 : (100+ic*ic%150)*0x010101;
          image.setRGB(x, y, c);
        }
      }

      panel.repaint(xStart, yStart, width, height);
    }
  }

}



