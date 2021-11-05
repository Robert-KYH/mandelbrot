import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Dimension;

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
    image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
  }

  @Override public void paintComponent(Graphics g) {  g.drawImage(image, 0, 0, null);  }
}
