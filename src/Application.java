import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


//  the main application class

class Application implements Runnable {

  private double centerX = -0.5, centerY = 0, zoomFactor = 4;
  private ImagePanel panel;
  private RenderManager manager;

  public void run() {
    //  a fixed-size frame (main window) which automatically quits the program when closed
    JFrame frame = new JFrame("Mandelbrot Fractal");
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //  a custom component holding the rendered image, slightly smaller than the screen
    Rectangle r = frame.getGraphicsConfiguration().getBounds();
    panel = new ImagePanel(r.width*4/5, r.height*4/5);

    //  call click() when clicking in the window
    panel.addMouseListener(new MouseAdapter() {  public void mouseClicked(MouseEvent e) {  click(e);  }  });

    //  put it all together and show the window
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);

    //  create the render manager and render the first view
    manager = new RenderManager(panel);
    manager.startRender(centerX, centerY, zoomFactor);
  }

  private void click(MouseEvent e) {
    if (manager.isWorking())  return;  //  ignore the click if currently rendering

    //  update the center and zoom factor and render the new view
    int w = panel.getImgWidth(), h = panel.getImgHeight();
    centerX = centerX+(e.getX()-w/2)*zoomFactor/w;
    centerY = centerY+(e.getY()-h/2)*zoomFactor/w;
    zoomFactor /= Main.MAG;
    manager.startRender(centerX, centerY, zoomFactor);
  }

}
