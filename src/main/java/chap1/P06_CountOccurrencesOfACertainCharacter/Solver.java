package chap1.P06_CountOccurrencesOfACertainCharacter;

public class Solver {

    public static int countOccurrencesOfACertainCharacterV1(String s, String ch) {
        if (ch.codePointCount(0, ch.length()) > 1) return -1;

        int result = s.length() - s.replace(ch, "").length();
        return ch.length() == 2 ? result / 2 : result;
    }

    public static long countOccurrencesOfACertainCharacterV2(String s, String ch) {
        if (ch.codePointCount(0, ch.length()) > 1) return -1;

        return s.codePoints()
                .filter(c -> c == ch.codePointAt(0))
                .count();
    }

    public static void main(String[] args) {
        String s = "😍 I love 💕 you Ӝ so much 💕 😍 🎼🎼🎼!";
        System.out.println(Solver.countOccurrencesOfACertainCharacterV1(s, "💕"));
        System.out.println(Solver.countOccurrencesOfACertainCharacterV2(s, "💕"));
    }
}
