package chap1.P14_FindACharacterWithMostAppearances;

import util.Pair;

import java.util.Collections;
import java.util.HashMap;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Solver {

    public static Pair<Character, Integer> maxOccurenceCharacterV1(String s) {
        HashMap<Character, Integer> counter = new HashMap<>();
        char[] chars = s.toCharArray();

        for (char cur : chars) {
            if (!Character.isWhitespace(cur)) {
                Integer freq = counter.get(cur);
                if (freq == null) counter.put(cur, 1);
                else counter.put(cur, ++freq);
            }
        }

        int maxOccurences = Collections.max(counter.values());
        char maxChar = Character.MIN_VALUE;

        for (var entry : counter.entrySet()) {
            if (entry.getValue() == maxOccurences) maxChar = entry.getKey();
        }

        return Pair.of(maxChar, maxOccurences);
    }

    public static Pair<Character, Long> maxOccurenceCharacterV2(String s) {
        return s.chars()
                .filter(c -> !Character.isWhitespace(c))
                .mapToObj(c -> (char) c)
                .collect(groupingBy(c -> c, counting()))
                .entrySet()
                .stream()
                .max(comparingByValue())
                .map(e -> Pair.of(e.getKey(), e.getValue()))
                .orElse(Pair.of(Character.MIN_VALUE, -1L));
    }
}
