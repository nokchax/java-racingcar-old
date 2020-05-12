package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("(.+)\\b([+\\-*/])(-?\\d+)");

    public static int calculate(String expression) {
        Matcher matcher = EXPRESSION_PATTERN.matcher(expression.replace(" ", ""));
        matcher.find();


        return Integer.parseInt(matcher.group(1)) + Integer.parseInt(matcher.group(3));
    }
}
