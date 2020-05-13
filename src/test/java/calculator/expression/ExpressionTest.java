package calculator.expression;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("수식 테스트")
class ExpressionTest {

    @Test
    @DisplayName("of 사용으로 객체 생성 확인")
    void constructor() {
        Expression expression = Expression.of("1", "+", "2");

        assertThatCode(() -> Expression.of("1", "+", "2"))
                .doesNotThrowAnyException();
        assertThat(expression).isNotNull();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("of 객체 생성시 exception 테스트")
    void ofException(final String subExp, final String operator, final String operand, final Class<?> exceptionClass) {
        assertThatThrownBy(() -> Expression.of(subExp, operator, operand))
                .isInstanceOf(exceptionClass);

    }

    private static Stream<Arguments> ofException() {
        return Stream.of(
                Arguments.of("", "+", "2", IllegalArgumentException.class),
                Arguments.of(null, "+", "2", IllegalArgumentException.class),
                Arguments.of("1", "", "2", IllegalArgumentException.class),
                Arguments.of("1", null, "2", IllegalArgumentException.class),
                Arguments.of("1", "+", "", NumberFormatException.class),
                Arguments.of("1", "+", null, NumberFormatException.class)
        );
    }

    @ParameterizedTest
    @DisplayName("테스트를 위한 equals 메소드 테스트")
    @CsvSource({"1+2,+,3", "1,+,2"})
    void equals(final String subExp, final String operator, final String operand) {
        Expression expression = Expression.of(subExp, operator, operand);
        Expression otherExp = Expression.of(subExp, operator, operand);

        assertThat(expression).isEqualTo(otherExp);
    }

    @ParameterizedTest
    @DisplayName("테스트를 위한 not equals 메소드 테스트")
    @CsvSource({"1+2,+,3,2+1,+,3", "1,+,2,2,+,1"})
    void equals(final String subExp, final String operator, final String operand,
                final String anotherSubExp, final String anotherOperator, final String anotherOperand) {
        Expression expression = Expression.of(subExp, operator, operand);
        Expression otherExp = Expression.of(anotherSubExp, anotherOperator, anotherOperand);

        assertThat(expression).isNotEqualTo(otherExp);
    }

    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    @CsvSource({"1,+,2,3", "1,-,2,-1", "1,*,2,2", "2,/,1,2", "1+2,+,3,6"})
    @DisplayName("수식의 계산이 올바른지")
    void operate(final String subExp, final String operator, final String operand, final int expected) {
        Expression expression = Expression.of(subExp, operator, operand);

        assertThat(expression.operate()).isEqualTo(expected);
    }
}