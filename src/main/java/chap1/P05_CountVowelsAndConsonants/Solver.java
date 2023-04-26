package chap1.P05_CountVowelsAndConsonants;

import util.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

public class Solver {

    private static final Set<Character> allVowels =
            new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static Pair<Integer, Integer> countVowelsAndConsonantsV1(String s) {
        s = s.toLowerCase();
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (allVowels.contains(ch)) vowels++;
            else if (ch >= 'a' && ch <= 'z') consonants++;
        }

        return Pair.of(vowels, consonants);
    }

    public static Pair<Long, Long> countVowelsAndConsonantsV2(String s) {
        s = s.toLowerCase();
        long vowels = s.chars()
                .filter(c -> allVowels.contains((char) c))
                .count();

        long consonants = s.chars()
                .filter(c -> !allVowels.contains((char) c))
                .filter(c -> (c >= 'a' && c <= 'z'))
                .count();

        return Pair.of(vowels, consonants);
    }

    public static Pair<Long, Long> countVowelsAndConsonantsV3(String s) {
        s = s.toLowerCase();
        Map<Boolean, Long> result = s.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> (c >= 'a' && c <= 'z'))
                .collect(partitioningBy(allVowels::contains, counting()));

        return Pair.of(result.get(true), result.get(false));
    }
}
