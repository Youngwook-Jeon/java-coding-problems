package chap1.P11_CheckPalindrome;

import java.util.stream.IntStream;

public class Solver {

    public static boolean isPalindromeV1(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static boolean isPalindromeV2(String s) {
        return IntStream.range(0, s.length() / 2)
                .noneMatch(p -> s.charAt(p) != s.charAt(s.length() - p - 1));
    }
}
