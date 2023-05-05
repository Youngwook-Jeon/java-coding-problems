package chap1.P18_CheckTwoStringsAnagrams;

import java.util.Arrays;

public class Solver {

    public static boolean isAnagramV1(String s1, String s2) {
        char[] chars1 = s1.replaceAll("\\s", "").toLowerCase().toCharArray();
        char[] chars2 = s2.replaceAll("\\s", "").toLowerCase().toCharArray();

        if (chars1.length != chars2.length) return false;

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }

    public static boolean isAnagramV2(String s1, String s2) {
        String chars1 = s1.replaceAll("\\s", "").toLowerCase();
        String chars2 = s2.replaceAll("\\s", "").toLowerCase();

        if (chars1.length() != chars2.length()) return false;

        return Arrays.equals(
                chars1.chars().sorted().toArray(),
                chars2.chars().sorted().toArray()
        );
    }
}
