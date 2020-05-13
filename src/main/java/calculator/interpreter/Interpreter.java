package calculator.interpreter;

import calculator.expression.Expression;
import calculator.expression.Number;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("(.+)\\b([+\\-*/])(-?\\d+)");
    public static final int SUB_EXPRESSION_GROUP_INDEX = 1;
    public static final int OPERATOR_GROUP_INDEX = 2;
    public static final int OPERAND_GROUP_INDEX = 3;

    private Interpreter() {}

    public static Expression interpret(String expressionString) {
        validate(expressionString);
        Matcher matcher = EXPRESSION_PATTERN.matcher(expressionString.replace(" ", ""));

        if(isNumber(matcher)) {
            return Number.of(expressionString);
        }

        return Expression.of(
                extractSubExpression(matcher),
                extractOperator(matcher),
                extractOperand(matcher)
        );
    }

    private static String extractOperand(Matcher matcher) {
        return matcher.group(OPERAND_GROUP_INDEX);
    }

    private static String extractOperator(Matcher matcher) {
        return matcher.group(OPERATOR_GROUP_INDEX);
    }

    private static String extractSubExpression(Matcher matcher) {
        return matcher.group(SUB_EXPRESSION_GROUP_INDEX);
    }

    private static boolean isNumber(Matcher matcher) {
        return !matcher.matches();
    }

    private static void validate(String expressionString) {
        if(expressionString == null || expressionString.isEmpty()) {
            throw new IllegalArgumentException("Expression is not valid : " + expressionString);
        }
    }


}
