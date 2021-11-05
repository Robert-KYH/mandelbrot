import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;

//  a custom java swing component that paints an image

class ImagePanel extends JPanel {
  private BufferedImage image;
  private int imgWidth, imgHeight;

  BufferedImage getImage() {  return image;  }
  int getImgWidth()        {  return imgWidth;  }
  int getImgHeight()       {  return imgHeight;  }

  ImagePanel(int w, int h) {
    super();
    imgWidth = w;
    imgHeight = h;

    //  set a fixed size for the component and the image
    setPreferredSize(new Dimension(w, h));
    setOpaque(true);
    image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
  }

  @Override public void paintComponent(Graphics g) {
    Rectangle r = g.getClipBounds();
    g.drawImage(image, r.x, r.y, r.x+r.width, r.y+r.height, r.x, r.y, r.x+r.width, r.y+r.height, null);
  }
}
