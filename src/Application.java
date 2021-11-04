import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

class Application implements Runnable {

  private double centerX = -0.5, centerY = 0, zoomFactor = 4;
  private ImagePanel panel;
  //private RenderManager manager = new RenderManager();

  public void run() {
    JFrame frame = new JFrame("Mandelbrot Fractal");
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Rectangle r = frame.getGraphicsConfiguration().getBounds();
    panel = new ImagePanel(r.width*4/5, r.height*4/5);
    render();
    panel.addMouseListener(new MouseAdapter() {  public void mouseClicked(MouseEvent e) {  click(e);  }  });

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }

  private void click(MouseEvent e) {
    int w = panel.width, h = panel.height;
    centerX = centerX+(e.getX()-w/2)*zoomFactor/w;
    centerY = centerY+(e.getY()-h/2)*zoomFactor/w;
    zoomFactor /= 3;
    render();
    panel.repaint();
  }

  private void render() {
    int w = panel.width, h = panel.height;
    BufferedImage img = panel.image;

    for (int y = 0; y < h; ++y) {
      for (int x = 0; x < w; ++x) {
        Complex z = new Complex(centerX+(x-w/2)*zoomFactor/w, centerY+(y-h/2)*zoomFactor/w);
        Complex z0 = new Complex(z);
        for (int i = 0; i++ < 100; ) z = z.sqr().plus(z0);
        int c = z.mag() < 2 ? 0x404040 : 0xb0b0b0;
        img.setRGB(x, y, c);
      }
    }

  }
}
