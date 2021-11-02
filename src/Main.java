import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class Main {
  public static void main(String[] args) {

    BufferedImage img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < 1000; ++y) {
      for (int x = 0; x < 1000; ++x) {

        Complex z = new Complex(x*0.002-1.5, y*0.002-1);
        Complex z0 = new Complex(z);

        for (int i = 0; i++ < 100;) {
          z = z.sqr().plus(z0);
        }

        int c = z.mag() < 2 ? 0x404040 : 0xb0b0b0;
        img.setRGB(x, y, c);
      }
    }

    try { ImageIO.write(img, "png", new File("mandelbrot.png")); }
    catch (Exception e) {}

  }
}
