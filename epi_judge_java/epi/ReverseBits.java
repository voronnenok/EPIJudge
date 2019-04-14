package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  static long mask = 0xFF;
  static int maxByte = 64;
  static int WORD_SIZE = 8;
  static long[] lookup = new long[(int)mask + 1];
  static {
    for (int i = 0; i < lookup.length; i++) {
      lookup[i] = precompute(i);
    }
  }

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    return usingLookupSolution(x);
  }

  public static long bruteForceSolution(long x) {
    for (int i = 0, j = 63; i < j; i++, j--) {
      if (((x >>> i) & 1) != ((x >>> j) & 1)) {
        long mask = (1L << i) | (1L << j);
        x ^= mask;
      }
    }
    return x;
  }

  public static long precompute(long x) {
    for (int i = 0, j = 7; i < j; i++, j--) {
      if (((x >>> i) & 1) != ((x >>> j) & 1)) {
        long mask = (1L << i) | (1L << j);
        x ^= mask;
      }
    }
    return x;
  }

  public static long usingLookupSolution(long x) {
    long result = 0;
    for (int i = 0, j = maxByte - WORD_SIZE; i < j; i += WORD_SIZE, j -= WORD_SIZE) {
      long left = lookup[(int)((x >> j) & mask)];
      long right = lookup[(int)((x >> i) & mask)];
      result |= left << i;
      result |= right << j;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
