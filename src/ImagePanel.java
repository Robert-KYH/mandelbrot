import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Dimension;


class ImagePanel extends JPanel {
  BufferedImage image;
  int width, height;

  ImagePanel(int w, int h) {
    super();
    width = w;
    height = h;
    setPreferredSize(new Dimension(w, h));
    image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
  }

  @Override public void paintComponent(Graphics g) {  g.drawImage(image, 0, 0, null);  }
}
