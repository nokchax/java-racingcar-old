package calculator.expression;

import calculator.interpreter.Interpreter;
import calculator.interpreter.MatchedExpression;
import calculator.operator.Operator;

import java.util.Objects;

public class ArithmeticExpression extends Expression {
    private Expression subExpression;
    private Operator operator;

    protected ArithmeticExpression() {}

    public static ArithmeticExpression of(MatchedExpression matchedExpression) {
        validate(matchedExpression);
        ArithmeticExpression expression = new ArithmeticExpression();

        expression.subExpression = Interpreter.interpret(matchedExpression.getSubExpressionString());
        expression.operand = Integer.parseInt(matchedExpression.getOperandString());
        expression.operator = Operator.of(matchedExpression.getOperatorString());

        return expression;
    }

    private static void validate(MatchedExpression matchedExpression) {
        if(matchedExpression == null) {
            throw new IllegalArgumentException("MatchedExpression is null");
        }

        if(matchedExpression.isNumberExpression()) {
            throw new IllegalArgumentException("MatchedExpression is not arithmetic expression");
        }
    }

    @Override
    public int calculate() {
        return operator.operate(subExpression.calculate(), operand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArithmeticExpression that = (ArithmeticExpression) o;
        return operand == that.operand &&
                Objects.equals(subExpression, that.subExpression) &&
                operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subExpression, operator, operand);
    }
}
