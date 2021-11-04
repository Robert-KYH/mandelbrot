import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

class Application implements Runnable {

  private double centerX = -0.5, centerY = 0, zoomFactor = 4;
  private ImagePanel panel;
  private RenderManager manager;

  public void run() {
    JFrame frame = new JFrame("Mandelbrot Fractal");
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Rectangle r = frame.getGraphicsConfiguration().getBounds();
    panel = new ImagePanel(r.width*4/5, r.height*4/5);
    panel.addMouseListener(new MouseAdapter() {  public void mouseClicked(MouseEvent e) {  click(e);  }  });

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);

    manager = new RenderManager(panel);
    manager.startRender(centerX, centerY, zoomFactor);
  }

  private void click(MouseEvent e) {
    if (manager.isWorking())  return;

    int w = panel.width, h = panel.height;
    centerX = centerX+(e.getX()-w/2)*zoomFactor/w;
    centerY = centerY+(e.getY()-h/2)*zoomFactor/w;
    zoomFactor /= 3;
    manager.startRender(centerX, centerY, zoomFactor);
  }

}
