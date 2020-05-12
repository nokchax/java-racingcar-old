package calculator.operator;

import calculator.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("null이거나 빈 문자열을 받았을때 예외를 발생시키는지")
    void ofException(String symbol) {
        assertThatThrownBy(() -> Operator.of(symbol))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_SUPPORTED_OPERATION);
    }
}