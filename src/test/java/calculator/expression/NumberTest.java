package calculator.expression;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static calculator.helper.Generator.matchedExpressionOf;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Number 클래스 테스트")
class NumberTest {

    @Test
    @DisplayName("of 사용으로 객체 생성 확인")
    void constructor() {
        Expression expression = Number.of(matchedExpressionOf("1"));

        assertThatCode(() -> Number.of(matchedExpressionOf("1")))
                .doesNotThrowAnyException();
        assertThat(expression).isNotNull();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("of 객체 생성시 exception 테스트")
    void ofException(final String exp) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Number.of(matchedExpressionOf(exp)));
    }

    private static Stream<String> ofException() {
        return Stream.of(
                "",
                null,
                "+"
        );
    }

    @ParameterizedTest
    @DisplayName("테스트를 위한 equals 메소드 테스트")
    @CsvSource({"-1", "1"})
    void equals(final String exp) {
        Number number = Number.of(matchedExpressionOf(exp));
        Number anotherNumber = Number.of(matchedExpressionOf(exp));

        assertThat(number).isEqualTo(anotherNumber);
    }

    @ParameterizedTest(name = "{0} != {1}")
    @DisplayName("테스트를 위한 not equals 메소드 테스트")
    @CsvSource({"-1,1", "1,-1"})
    void notEquals(final String exp, final String anotherExp) {
        Number number = Number.of(matchedExpressionOf(exp));
        Number anotherNumber = Number.of(matchedExpressionOf(anotherExp));

        assertThat(number).isNotEqualTo(anotherNumber);
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource({"1,1", "-1,-1"})
    @DisplayName("수식의 계산이 올바른지")
    void operate(final String subExp, final int expected) {
        Number number = Number.of(matchedExpressionOf(subExp));

        assertThat(number.calculate()).isEqualTo(expected);
    }
}