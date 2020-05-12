package calculator.operator;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static calculator.exception.ErrorMessage.NOT_SUPPORTED_OPERATION;

public enum Operator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private static final Map<String, Operator> OPERATORS =
            Arrays.stream(Operator.values())
                    .collect(Collectors.toMap(Operator::getSymbol, Function.identity()));

    private final String symbol;

    Operator(final String symbol) {
        this.symbol = symbol;
    }

    private String getSymbol() {
        return symbol;
    }

    public static Operator of(final String symbol) {
        if(!OPERATORS.containsKey(symbol)) {
            throw new IllegalArgumentException(NOT_SUPPORTED_OPERATION + " : " + symbol);
        }

        return OPERATORS.get(symbol);
    }
}
