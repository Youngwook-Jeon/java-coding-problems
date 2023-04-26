package chap1.P03_ReverseWords;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solver {

    private static final String WHITESPACE = " ";
    private static final Pattern PATTERN = Pattern.compile(" +");

    public static String ReverseWordsV1(String s) {
        String[] words = s.split(WHITESPACE);
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            StringBuilder sb = new StringBuilder();

            for (int i = word.length() - 1; i >= 0; i--) {
                sb.append(word.charAt(i));
            }
            result.append(sb).append(WHITESPACE);
        }
        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }

    public static String ReverseWordsV2(String s) {
        return PATTERN.splitAsStream(s)
                .map(word -> new StringBuilder(word).reverse())
                .collect(Collectors.joining(WHITESPACE));
    }

    public static void main(String[] args) {
        String text = "Yesterday I was clever, so I wanted to change the world. Today I am wise, so I am changing myself.";
        System.out.println(Solver.ReverseWordsV1(text));
        System.out.println(Solver.ReverseWordsV2(text));
    }
}
