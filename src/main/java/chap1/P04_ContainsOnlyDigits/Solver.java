package chap1.P04_ContainsOnlyDigits;

public class Solver {

    public static boolean containsOnlyDigitsV1(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    public static boolean containsOnlyDigitsV2(String s) {
        return s.chars()
                .allMatch(Character::isDigit);
    }

    public static boolean containsOnlyDigitsV3(String s) {
        return s.matches("[0-9]+");
    }
}
