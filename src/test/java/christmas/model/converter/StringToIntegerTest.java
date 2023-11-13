package christmas.model.converter;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringToIntegerTest {
    StringToInteger stringToInteger;

    @BeforeEach
    void beforeEach() {
        stringToInteger = new StringToInteger();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "31", "0", "32", "-2147483648", "2147483647"})
    @DisplayName("입력값이 Integer인 경우 어떠한 예외도 반환되지 않는다.")
    void stringToIntegerSuccess(String input) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> stringToInteger.toType(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-2147483649", "2147483648"})
    @DisplayName("입력값이 Integer가 아닐 경우 예외가 반환된다.")
    void stringToIntegerFailure(String input) {
        // given
        // when & then
        assertThatExceptionOfType(NumberFormatException.class)
                .isThrownBy(() -> stringToInteger.toType(input));
    }
}