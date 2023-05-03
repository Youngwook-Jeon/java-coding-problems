package chap1.P13_RemoveGivenCharacter;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solver {

    public static String removeCharacterV1(String s, char ch) {
        return s.replaceAll(Pattern.quote(String.valueOf(ch)), "");
    }

    public static String removeCharacterV2(String s, String ch) {
        int codePoint = ch.codePointAt(0);

        return s.codePoints()
                .filter(cp -> cp != codePoint)
                .mapToObj(cp -> String.valueOf(Character.toChars(cp)))
                .collect(Collectors.joining());
    }
}
