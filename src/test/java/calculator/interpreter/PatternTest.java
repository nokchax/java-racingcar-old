package calculator.interpreter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("수식 패턴 테스트")
public class PatternTest {
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("(.+)\\b([+\\-*/])(-?\\d+)");
    private static final Pattern PATTERN = Pattern.compile("(-?\\d+)([+\\-*/])(.+)");

    @ValueSource(strings = {"1+2+3", "-1+-2+-3", "10+-20--20*-10/-10"})
    @ParameterizedTest
    void patternCompile(String expression) {
        Matcher matcher = PATTERN.matcher(expression);

        assertThat(matcher.matches()).isTrue();

        System.out.println("operand : " + matcher.group(1));
        System.out.println("operator : " + matcher.group(2));
        System.out.println("subExp : " + matcher.group(3));
    }
}
