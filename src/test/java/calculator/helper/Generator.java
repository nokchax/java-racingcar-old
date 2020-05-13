package calculator.helper;

import calculator.interpreter.MatchedExpression;

public class Generator {
    public static MatchedExpression matchedExpressionOf(String subExpression, String operator, String operand) {
        return new MatchedExpression(subExpression + operator + operand);
    }

    public static MatchedExpression matchedExpressionOf(String operand) {
        return new MatchedExpression(operand);
    }
}
