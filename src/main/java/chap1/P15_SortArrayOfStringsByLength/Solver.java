package chap1.P15_SortArrayOfStringsByLength;

import java.util.Arrays;
import java.util.Comparator;

public class Solver {

    public enum Sort {
        ASC, DESC
    }

    public static void sortArrayByLengthV1(String[] s, Sort dir) {
        if (dir.equals(Sort.ASC)) {
            Arrays.sort(s, (String s1, String s2) -> Integer.compare(s1.length(), s2.length()));
        } else {
            Arrays.sort(s, (String s1, String s2) -> (-1) * Integer.compare(s1.length(), s2.length()));
        }
    }

    public static void sortArrayByLengthV2(String[] s, Sort dir) {
        if (dir.equals(Sort.ASC)) {
            Arrays.sort(s, Comparator.comparingInt(String::length));
        } else {
            Arrays.sort(s, Comparator.comparingInt(String::length).reversed());
        }
    }

    public static String[] sortArrayByLengthV3(String[] s, Sort dir) {
        if (dir.equals(Sort.ASC)) {
            return Arrays.stream(s)
                    .sorted(Comparator.comparingInt(String::length))
                    .toArray(String[]::new);
        } else {
            return Arrays.stream(s)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .toArray(String[]::new);
        }
    }
}
