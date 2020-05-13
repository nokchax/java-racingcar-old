package calculator.expression;


import calculator.interpreter.MatchedExpression;

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
}
