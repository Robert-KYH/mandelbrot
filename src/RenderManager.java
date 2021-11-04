import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.awt.image.BufferedImage;

class RenderManager {
  private boolean isWorking = false;
  private BufferedImage image;
  private int width, height;
  private ImagePanel panel;

  private double xx, yy, zz;

  boolean isWorking() {  return this.isWorking;  }

  RenderManager(ImagePanel p) {
    panel = p;
    image = panel.image;
    width = panel.width;
    height = panel.height;
  }

  void startRender(double x, double y, double z) {
    isWorking = true;
    xx = x;
    yy = y;
    zz = z;
    ForkJoinPool.commonPool().invoke(new RenderTask(0, 0, width, height));

    isWorking = false;
  }



  // inre klass
  private class RenderTask extends RecursiveAction {

    private int xs, ys, w, h;

    RenderTask(int x, int y, int w, int h) {
      xs = x;
      ys = y;
      this.w = w;
      this.h = h;
    }

    public void compute() {
      if (w * h > 5000) recurse();
      else render();
    }


    private void recurse() {
      if (w > h) invokeAll(new RenderTask(xs, ys, w / 2, h),
              new RenderTask(xs + w / 2, ys, w - w / 2, h));

      else invokeAll(new RenderTask(xs, ys, w, h / 2),
              new RenderTask(xs, ys + h / 2, w, h - h / 2));
    }


    private void render() {

      for (int y = ys; y < ys + h; ++y) {
        for (int x = xs; x < xs + w; ++x) {
          Complex z = new Complex(xx + (x - width / 2) * zz / width, yy + (y - height / 2) * zz / width);
          Complex z0 = new Complex(z);
          for (int i = 0; i++ < 500; ) z = z.sqr().plus(z0);
          int c = z.mag() < 2 ? 0x404040 : 0xb0b0b0;
          image.setRGB(x, y, c);
        }
      }

      panel.repaint(0, xs, ys, w, h);
    }

  }

}



