package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

}