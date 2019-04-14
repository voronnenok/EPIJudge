package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    return secondTrySolution(x);
  }

  public static boolean bruteForceSolution(int x) {
    char[] value = String.valueOf(x).toCharArray();

    for (int i = 0, j = 0; i < (value.length / 2); i++, j ++) {
      if (value[i] == value[value.length - 1 - j]) {
        continue;
      }
      return false;
    }
    return true;
  }

  public static boolean secondTrySolution(int x) {
    if (x < 0) {
      return false;
    }

    int numberOfDigits = (int) Math.floor(Math.log10(x)) + 1;

    for (int i = 0, j = numberOfDigits - 1; i < numberOfDigits / 2; i++) {
      int most = (int)Math.pow(10, j);
      int left = (int)Math.floor(x / most);
      int right = x % 10;
      if (left == right) {
        x = ((x % most) - right) / 10;
        j -= 2;
        continue;
      }
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
