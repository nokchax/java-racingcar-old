package calculator.operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("연산자 enum 테스트")
class OperatorTest {

    @MethodSource
    @ParameterizedTest(name = "{0} -> {1}")
    @DisplayName("문자열을 받았을때 해당 연산자를 잘 리턴하는지")
    void of(String symbol, Operator expectedOperator) {
        assertThat(Operator.of(symbol)).isEqualByComparingTo(expectedOperator);
    }

    private static Stream<Arguments> of() {
        return Stream.of(
                Arguments.of("+", Operator.PLUS),
                Arguments.of("-", Operator.MINUS),
                Arguments.of("*", Operator.MULTIPLY),
                Arguments.of("/", Operator.DIVIDE)
        );
    }
}