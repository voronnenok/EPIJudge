package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    return exponential(x, y);
  }

  public static double bruteForce(double x, int y) {
    double result = x;
    int power = Math.abs(y);
    while (power-- > 1) {
      result *= x;
    }
    return y > 0 ? result : y == 0 ? 1 : 1/result;
  }

  public static double exponential(double x, int y) {
    double result = 1;
    double lastX = x;
    int power = Math.abs(y);
    while (power > 0) {
      if ((power & 1) == 1) {
        result *= lastX;
      }
      power >>= 1;
      lastX *= lastX;
    }
    return y >= 0 ? result : 1/result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
