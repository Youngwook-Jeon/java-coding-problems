package chap1.P16_CheckStringContainsSubstring;

import java.util.regex.Pattern;

public class Solver {

    public static boolean containsV1(String text, String subText) {
        return text.contains(subText);
    }

    public static boolean containsV2(String text, String subText) {
        return text.indexOf(subText) != -1;
    }

    public static boolean containsV3(String text, String subText) {
        return text.matches("(?i).*" + Pattern.quote(subText) + ".*");
    }
}
