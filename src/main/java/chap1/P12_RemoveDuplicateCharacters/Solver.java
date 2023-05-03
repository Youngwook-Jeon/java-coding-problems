package chap1.P12_RemoveDuplicateCharacters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solver {

    public static String removeDuplicatesV1(String s) {
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        char[] charArr = s.toCharArray();

        for (char c : charArr) {
            if (set.add(c)) sb.append(c);
        }

        return sb.toString();
    }

    public static String removeDuplicatesV2(String s) {
        return Arrays.stream(s.split(""))
                .distinct()
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicatesV2("aabbccccdde"));
    }
}
