package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    return fast(x);
  }

  public static long fast(long x) {
    if ((x & 1) == 0) {
      long leastBit = x ^ (x & (x - 1));
      long mask = leastBit | (leastBit >> 1);
      return x ^ mask;
    } else {
      return bruteForce(x);
    }
  }

  public static long bruteForce(long x) {
    long mask = 3;
    while (mask > 0) {
      long y = (x & mask);
      if (y != mask && y != 0) {
        return (x ^ mask);
      }
      mask <<= 1;
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
