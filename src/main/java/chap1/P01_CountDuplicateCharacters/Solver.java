package chap1.P01_CountDuplicateCharacters;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solver {

    public static Map<Character, Long> countDuplicateCharactersV1(String s) {
        Map<Character, Long> result = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            result.compute(s.charAt(i), (k, v) -> (v == null) ? 1 : ++v);
        }

        return result;
    }

    public static Map<Character, Long> countDuplicateCharactersV2(String s) {
        return s.chars() // IntStream
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    // 위 두 방법은 유니코드 16비트문자의 추가 문자들을 제대로 표현할 수 없기 때문에 다음과 같이 수정
    public static Map<String, Long> countDuplicateCharactersWithUnicodeV1(String s) {
        Map<String, Long> result = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int cp = s.codePointAt(i);
            String ch = String.valueOf(Character.toChars(cp));
            if (Character.charCount(cp) == 2) i++;

            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }

        return result;
    }

    public static Map<String, Long> countDuplicateCharactersWithUnicodeV2(String s) {
        return s.codePoints() // IntStream
                .mapToObj(c -> String.valueOf(Character.toChars(c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    public static void main(String[] args) {
        String TEXT = "😍 I love 💕 you Ӝ so much 💕 😍 🎼🎼🎼!";
        Map<String, Long> result = Solver.countDuplicateCharactersWithUnicodeV2(TEXT);
        result.forEach((k, l) -> System.out.println("k: " + k + " v " + l));
    }
}
