package calculator.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchedExpression {
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("(.+)\\b([+\\-*/])(-?\\d+)");
    private static final int SUB_EXPRESSION_GROUP_INDEX = 1;
    private static final int OPERATOR_GROUP_INDEX = 2;
    private static final int OPERAND_GROUP_INDEX = 3;
    private static final String BLANK = " ";
    private static final String EMPTY = "";

    private Matcher matcher;

    private MatchedExpression() {}

    public static MatchedExpression match(String expressionString) {
        validate(expressionString);

        MatchedExpression matchedExpression = new MatchedExpression();
        matchedExpression.matcher = EXPRESSION_PATTERN.matcher(expressionString.replace(BLANK, EMPTY));

        return matchedExpression;
    }

    public boolean isNumberExpression() {
        return !matcher.matches();
    }

    public String getSubExpressionString() {
        return matcher.group(SUB_EXPRESSION_GROUP_INDEX);
    }

    public String getOperatorString() {
        return matcher.group(OPERATOR_GROUP_INDEX);
    }

    public String getOperandString() {
        if(isNumberExpression()) {
            return matcher.group();
        }

        return matcher.group(OPERAND_GROUP_INDEX);
    }

    private static void validate(String expressionString) {
        if(expressionString == null || expressionString.isEmpty()) {
            throw new IllegalArgumentException("Expression is not valid : " + expressionString);
        }
    }
}
