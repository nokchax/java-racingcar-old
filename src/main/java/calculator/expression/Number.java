package calculator.expression;


import calculator.interpreter.MatchedExpression;

import java.util.Objects;

public class Number extends Expression {

    private Number(final String numberString) {
        validate(numberString);

        this.operand = Integer.parseInt(numberString);
    }

    public static Number of(MatchedExpression matchedExpression) {
        return new Number(matchedExpression.getOperandString());
    }

    @Override
    public int calculate() {
        return operand;
    }

    private void validate(String numberString) {
        if(numberString == null || numberString.isEmpty()) {
            throw new IllegalArgumentException("Number string is null or empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number that = (Number) o;
        return operand == that.operand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand);
    }
}
