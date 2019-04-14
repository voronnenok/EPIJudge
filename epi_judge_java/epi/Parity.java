package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  static final short[] lookupTable = new short[Integer.MAX_VALUE-1];

  static {
    boolean isParity = true;
    for (int i = 0; i < lookupTable.length; i++) {
      lookupTable[i] = (short)(isParity ? 0 : 1);
      isParity = !isParity;
    }
  }
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
//    int result = 0;
//    while(x != 0) {
//      result += x & 1;
//      x>>>=1;
//    }
//    return (short) (result & 1);
    long result = 0;
    while(x != 0) {
      int lookupKey = (short)(x & (Integer.MAX_VALUE - 1));
      long value = lookupTable[lookupKey];
      result += value;
      x>>>=16;
    }
    return (short) (result & 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
