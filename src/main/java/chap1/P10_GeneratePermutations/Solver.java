package chap1.P10_GeneratePermutations;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solver {

    public static void permuteAndPrintStream(String prefix, String s) {
        int n = s.length();

        if (n == 0) {
            System.out.print(prefix + " ");
        } else {
            IntStream.range(0, n)
                    .parallel()
                    .forEach(i -> permuteAndPrintStream(
                            prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, n)));
        }
    }

    public static Stream<String> permuteString(String s) {
        if (s == null || s.isBlank()) {
            return Stream.of("");
        }

        return IntStream.range(0, s.length())
                .parallel()
                .boxed()
                .flatMap(i ->
                        permuteString(s.substring(0, i) + s.substring(i + 1))
                                .map(sub -> s.charAt(i) + sub));
    }

    public static void main(String[] args) {
        String s = "TEST";
//        Solver.permuteAndPrintStream("", s);
        Stream<String> stringStream = Solver.permuteString(s);
        stringStream.forEach(System.out::println);
    }
}
