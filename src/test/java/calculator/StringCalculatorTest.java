package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("문자열 계산기 테스트")
class StringCalculatorTest {

    @MethodSource
    @DisplayName("덧셈 테스트")
    @ParameterizedTest(name = "{0} + {1} = {2}")
    void add(int x, int y, int expectedResult) {
        int result = StringCalculator.calculate(x + " + " + y);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> add() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(1, -2, -1),
                Arguments.of(-1, 2, 1),
                Arguments.of(-1, -2, -3)
        );
    }

    @MethodSource
    @DisplayName("뺄셈 테스트")
    @ParameterizedTest(name = "{0} - {1} = {2}")
    void sub(int x, int y, int expectedResult) {
        int result = StringCalculator.calculate(x + " - " + y);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> sub() {
        return Stream.of(
                Arguments.of(1, 2, -1),
                Arguments.of(1, -2, 3),
                Arguments.of(-1, 2, -3),
                Arguments.of(-1, -2, 1)
        );
    }

    @MethodSource
    @DisplayName("곱셈 테스트")
    @ParameterizedTest(name = "{0} * {1} = {2}")
    void multi(int x, int y, int expectedResult) {
        int result = StringCalculator.calculate(x + " * " + y);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> multi() {
        return Stream.of(
                Arguments.of(1, 2, 2),
                Arguments.of(1, -2, -2),
                Arguments.of(-1, 2, -2),
                Arguments.of(-1, -2, 2)
        );
    }

    @MethodSource
    @DisplayName("나눗셈 테스트")
    @ParameterizedTest(name = "{0} / {1} = {2}")
    void divide(int x, int y, int expectedResult) {
        int result = StringCalculator.calculate(x + " / " + y);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> divide() {
        return Stream.of(
                Arguments.of(2, 1, 2),
                Arguments.of(2, -1, -2),
                Arguments.of(-2, 1, -2),
                Arguments.of(-2, -1, 2)
        );
    }

    @MethodSource
    @DisplayName("복잡한 연산 테스트")
    @ParameterizedTest(name = "{0} = {1}")
    void complexExp(String exp, int expectedResult) {
        int result = StringCalculator.calculate(exp);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> complexExp() {
        return Stream.of(
                Arguments.of("1+2+3", 6),
                Arguments.of("1+2*3/3", 3),
                Arguments.of("-40+-30--20*-10/-5", -100)
        );
    }
}