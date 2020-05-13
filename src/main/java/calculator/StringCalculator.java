package calculator;

import calculator.expression.Expression;
import calculator.interpreter.Interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static int calculate(String expressionString) {
        Expression expression = Interpreter.interpret(expressionString);

        return expression.operate();
    }
}
