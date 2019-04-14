package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    int sign = x / Math.abs(x);
    x = Math.abs(x);
    int numberOfDigits = (int) Math.floor(Math.log10(x)) + 1;

    int maxPower  = numberOfDigits - 1;
    int currentMaxPower = maxPower;
    long result = 0;
    for (int minPower = 0; minPower <= maxPower; minPower++, maxPower--) {
      int left = (int) Math.floor(x / Math.pow(10, currentMaxPower));
      int right = x % 10;
      result += left * Math.pow(10, minPower);
      if (minPower != maxPower) {
        result += right * Math.pow(10, maxPower);
      }
      x = (int)(x - left * Math.pow(10, currentMaxPower) - right) / 10;
      currentMaxPower -= 2;
    }
    return result * sign;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
