package christmas.model.validator;

import static christmas.util.exceptions.Exceptions.DATE_OF_VISIT_INVALID;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateOfVisitValidatorTest {
    DateOfVisitValidator dateOfVisitValidator;

    @BeforeEach
    void beforeEach() {
        dateOfVisitValidator = new DateOfVisitValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "31", "0", "32", "-2147483649", "2147483648"})
    @DisplayName("입력값이 숫자인 경우 어떠한 예외도 반환되지 않는다.")
    void numericCheckSuccess(String dateOfVisit) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> dateOfVisitValidator.validateNumeric(dateOfVisit));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "-", ","})
    @DisplayName("입력값이 숫자가 아닌 경우 예외가 반환된다.")
    void numericCheckFailure(String dateOfVisit) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dateOfVisitValidator.validateNumeric(dateOfVisit))
                .withMessage(DATE_OF_VISIT_INVALID.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 16, 31})
    @DisplayName("입력값이 1이상 31이하일 경우 어떠한 예외도 반환되지 않는다.")
    void rangeCheckSuccess(int dateOfVisit) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> dateOfVisitValidator.validateRange(dateOfVisit));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32, -2147483648, 2147483647})
    @DisplayName("입력값이 1이상 31이하가 아닐 경우 예외가 반환된다.")
    void rangeCheckFailure(int dateOfVisit) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dateOfVisitValidator.validateRange(dateOfVisit))
                .withMessage(DATE_OF_VISIT_INVALID.getMessage());
    }
}