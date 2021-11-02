//  minimal implementation of a class representing a complex number backed by double-precision floating-point

class Complex {
  private double re, im;

  double getRe() {  return re;  }
  double getIm() {  return im;  }

  Complex(double r, double i) {  re = r;          im = i;  }
  Complex(Complex c)          {  re = c.getRe();  im = c.getIm();  }

  //  two numbers are equal if their real and imaginary parts are equal
  @Override public boolean equals(Object o) {  return o instanceof Complex  &&  re == ((Complex)o).getRe()  &&  im == ((Complex)o).getIm();  }

  Complex plus(Complex c) {  return new Complex(re+c.getRe(), im+c.getIm());  }  //  compute a+b
  Complex sqr()           {  return new Complex(re*re - im*im, 2*re*im);      }  //  compute c²
  double mag()            {  return java.lang.Math.sqrt(re*re + im*im);       }  //  compute |c|
}
