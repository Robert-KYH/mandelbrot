import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Dimension;


class ImagePanel extends JPanel {
  private BufferedImage image;

  ImagePanel(int w, int h) {
    super();
    setPreferredSize(new Dimension(w, h));
    image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < h; ++y) {
      for (int x = 0; x < w; ++x) {
        Complex z = new Complex(x*0.003-2, y*0.003-1);
        Complex z0 = new Complex(z);
        for (int i = 0; i++ < 100; ) z = z.sqr().plus(z0);
        int c = z.mag() < 2 ? 0x404040 : 0xb0b0b0;
        image.setRGB(x, y, c);
      }
    }

  }

  @Override public void paintComponent(Graphics g) {  g.drawImage(image, 0, 0, null);  }
}
