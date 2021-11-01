import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class Main {
  public static void main(String[] args) {

    BufferedImage img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < 1000; ++y) {
      for (int x = 0; x < 1000; ++x) {
        double re = x*0.002-1.5, im = y*0.002-1, re0 = re, im0 = im;

        for (int i = 0; i++ < 100;) {
          double re_temp = re*re - im*im + re0;
          im = 2*re*im + im0;
          re = re_temp;
        }

        int c = (re*re - im*im + 2*re*im < 4) ? 0x404040 : 0xb0b0b0;
        img.setRGB(x, y, c);
      }
    }

    try { ImageIO.write(img, "png", new File("mandelbrot.png")); }
    catch (Exception e) {}

  }
}
