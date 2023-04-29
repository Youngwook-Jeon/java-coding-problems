package chap1.P09_JoinMultipleStrings;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Solver {

    public static String joinByDelimiterV1(char delimiter, String... args) {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        for (i = 0; i < args.length - 1; i++) {
            sb.append(args[i]).append(delimiter);
        }
        sb.append(args[i]);

        return sb.toString();
    }

    public static String joinByDelimiterV2(char delimiter, String... args) {
        StringJoiner stringJoiner = new StringJoiner(String.valueOf(delimiter));

        for (String arg : args) {
            stringJoiner.add(arg);
        }

        return stringJoiner.toString();
    }

    public static String joinByDelimiterV3(char delimiter, String... args) {
        return Arrays.stream(args, 0, args.length)
                .collect(Collectors.joining(String.valueOf(delimiter)));
    }
}
