package calculator;

import calculator.expression.Expression;
import calculator.interpreter.Interpreter;

public class StringCalculator {
    public static int calculate(String expressionString) {
        Expression arithmeticExpression = Interpreter.interpret(expressionString);

        return arithmeticExpression.calculate();
    }
}
