package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    long ai = (long)Math.pow(2, i);
    long aj = (long)Math.pow(2, j);
    long difi = x & ai;
    long difj = x & aj;
    if(difi > 0 && difj > 0 || difi == difj) {
      return x;
    }
    return x ^ ai ^ aj;
  }

//  /***
//   * Book solution
//   */
//  @EpiTest(testDataFile = "swap_bits.tsv")
//  public static long swapBits(long x, int i, int j) {
//    if(((x >>> i) & 1L) == ((x >>> j) & 1L)) {
//      return x;
//    }
//    return x ^ ((1L << i) | (1L << j));
//  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
