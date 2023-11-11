package christmas.model.validator;

import static christmas.util.exceptions.Exceptions.DATE_OF_VISIT_INVALID;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.BeforeEach;
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
    void numericCheckSuccess(String dateOfVisit) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> dateOfVisitValidator.validateNumeric(dateOfVisit));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "-", ","})
    void numericCheckFailure(String dateOfVisit) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dateOfVisitValidator.validateNumeric(dateOfVisit))
                .withMessage(DATE_OF_VISIT_INVALID.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 16, 31})
    void rangeCheckSuccess(int dateOfVisit) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> dateOfVisitValidator.validateRange(dateOfVisit));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32, -2147483648, 2147483647})
    void rangeCheckFailure(int dateOfVisit) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> dateOfVisitValidator.validateRange(dateOfVisit))
                .withMessage(DATE_OF_VISIT_INVALID.getMessage());
    }
}