package christmas.model.validator;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TypeValidatorTest {
    TypeValidator typeValidator;

    @BeforeEach
    void beforeEach() {
        typeValidator = new TypeValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "31", "0", "32", "-2147483649", "2147483648"})
    @DisplayName("입력값이 숫자인 경우 어떠한 예외도 반환되지 않는다.")
    void numericCheckSuccess(String input) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> typeValidator.checkIsNumeric(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "-", ","})
    @DisplayName("입력값이 숫자가 아닌 경우 예외가 반환된다.")
    void numericCheckFailure(String input) {
        // given
        // when & then
        assertThatExceptionOfType(NumberFormatException.class)
                .isThrownBy(() -> typeValidator.checkIsNumeric(input));
    }
}