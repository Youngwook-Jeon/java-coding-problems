package chap1.P17_CountStringInString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {

    // If string: 111, toFind: 11 -> We get 2
    public static int countStringInString(String string, String toFind) {
        Pattern pattern = Pattern.compile(Pattern.quote(toFind));
        Matcher matcher = pattern.matcher(string);

        int pos = 0;
        int count = 0;

        while (matcher.find(pos)) {
            pos = matcher.start() + 1;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(Solver.countStringInString("111", "11"));
    }
}
