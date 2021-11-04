import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Application implements Runnable {

  //private double centerX = 0, centerY = 0, zoomFactor = 2;
  //private RenderManager manager = new RenderManager();

  public void run() {
    JFrame frame = new JFrame("Mandelbrot Fractal");
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Rectangle r = frame.getGraphicsConfiguration().getBounds();
    ImagePanel panel = new ImagePanel(r.width*4/5, r.height*4/5);
    //panel.addMouseListener(new MouseAdapter() {  public void mouseClicked(MouseEvent e) {  click(e);  }  });

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
  /*
  private void click(MouseEvent e) {
  }
  */
}
