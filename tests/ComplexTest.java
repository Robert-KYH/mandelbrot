import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {

  //  tests for squaring a number

  //  1.  the number (0, 0) becomes (0, 0) when squared
  @Test void sqr1() {
    Complex c = new Complex(0, 0);
    assertEquals(c.sqr(), c);
  }

  //  2.  numbers on the imaginary axis will fold to the negative real axis
  @Test void sqr2() {
    boolean err = false;

    for (double i = -10; i <= 10;  i += 0.1) {
      Complex c = new Complex(0, i).sqr();
      if (c.getRe() > 0  ||  c.getIm() != 0)  err = true;
    }

    assertFalse(err);
  }

  //  3.  numbers on the negative real axis will flip to the positive real axis
  @Test void sqr3() {
    boolean err = false;

    for (double r = -10; r <= 0;  r += 0.1) {
      Complex c = new Complex(r, 0).sqr();
      if (c.getRe() < 0  ||  c.getIm() != 0)  err = true;
    }

    assertFalse(err);
  }

  //  4.  numbers on the positive real axis will remain on the positive real axis
  @Test void sqr4() {
    boolean err = false;

    for (double r = 10; r >= 0;  r -= 0.1) {
      Complex c = new Complex(r, 0).sqr();
      if (c.getRe() < 0  ||  c.getIm() != 0)  err = true;
    }

    assertFalse(err);
  }

  //  5.  squaring a complex number will also square its magnitude
  @Test void sqr5() {
    Complex c = new Complex(6, -11);
    assertTrue(c.mag()*c.mag() == c.sqr().mag());
  }



  //  tests for computing the sum

  //  1.  basic test
  @Test void plus1() {
    Complex c1 = new Complex(1, 2);
    Complex c2 = new Complex(3, 4);
    assertEquals(c1.plus(c2), new Complex(4, 6));
  }

  //  2.  test commutativity
  @Test void plus2() {
    Complex c1 = new Complex(1, 2);
    Complex c2 = new Complex(3, 4);
    assertEquals(c1.plus(c2), c2.plus(c1));
  }



  //  tests for computing the magnitude

  //  1.  (0, 0) has a magnitude of 0
  @Test void mag1() {
    Complex c = new Complex(0, 0);
    assertTrue(c.mag() == 0);
  }

  //  2.  basic hypotenuse test, 3²+4² = 5²
  @Test void mag2() {
    Complex c = new Complex(3, 4);
    assertTrue(c.mag() == 5);
  }

  //  3.  mirroring a number across the origo does not change its magnitude
  @Test void mag3() {
    Complex c1 = new Complex(5,  -17);
    Complex c2 = new Complex(-c1.getIm(), -c1.getRe());
    assertTrue(c1.mag() == c2.mag());
  }

}
