package calculator.expression;

import calculator.interpreter.Interpreter;
import calculator.operator.Operator;

import java.util.Objects;

public class Expression {
    private Expression subExpression;
    private Operator operator;
    protected int operand;

    protected Expression() {}

    public static Expression of(String subExpression, String operator, String operand) {
        Expression expression = new Expression();

        expression.subExpression = Interpreter.interpret(subExpression);
        expression.operand = Integer.parseInt(operand);
        expression.operator = Operator.of(operator);

        return expression;
    }

    public int operate() {
        return operator.operate(subExpression.operate(), operand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return operand == that.operand &&
                Objects.equals(subExpression, that.subExpression) &&
                operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subExpression, operator, operand);
    }
}
