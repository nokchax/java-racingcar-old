package calculator.operator;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Operator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private static final Map<String, Operator> OPERATORS =
            Arrays.stream(Operator.values())
                    .collect(Collectors.toMap(Operator::getSymbol, Function.identity()));

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    private String getSymbol() {
        return symbol;
    }

    public static Operator of(String symbol) {
        if(!OPERATORS.containsKey(symbol)) {
            throw new IllegalArgumentException("Not supported operation : " + symbol);
        }

        return OPERATORS.get(symbol);
    }
}
