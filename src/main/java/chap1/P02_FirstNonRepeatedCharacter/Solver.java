package chap1.P02_FirstNonRepeatedCharacter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solver {

    public static String firstNonRepeatedCharacter(String s) {
        LinkedHashMap<Integer, Long> chs = s.codePoints()
                .boxed()
                .collect(Collectors.groupingBy(
                        Function.identity(), LinkedHashMap::new, Collectors.counting()));

        Integer result = chs.entrySet().stream()
                .filter(e -> e.getValue() == 1L)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse((int) Character.MIN_VALUE);

        return String.valueOf(Character.toChars(result));
    }

    public static void main(String[] args) {
        String TEXT = "😍the short repeated string😍!";
        System.out.println(Solver.firstNonRepeatedCharacter(TEXT)); // o
    }
}
