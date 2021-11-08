
class Main {
  static final double MAG = 3;        //  magnifying factor when zooming in
  static final int ITER = 1000;        //  number of iterations (detail) when rendering
  static final int THRESHOLD = 5000;  //  threshold for when the multi-threaded renderer will recurse

  public static void main(String[] args) {  javax.swing.SwingUtilities.invokeLater(new Application());  }
}
