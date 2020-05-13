package calculator.helper;

import calculator.interpreter.MatchedExpression;

public class Generator {
    public static MatchedExpression matchedExpressionOf(String subExpression, String operator, String operand) {
        return MatchedExpression.match(subExpression + operator + operand);
    }

    public static MatchedExpression matchedExpressionOf(String operand) {
        return MatchedExpression.match(operand);
    }
}
