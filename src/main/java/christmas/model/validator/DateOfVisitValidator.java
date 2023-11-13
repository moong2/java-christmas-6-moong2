package christmas.model.validator;

import static christmas.model.util.event.EventPeriod.EVENT_DAY_END;
import static christmas.model.util.event.EventPeriod.EVENT_DAY_START;
import static christmas.util.exceptions.Exceptions.DATE_OF_VISIT_INVALID;

public class DateOfVisitValidator {

    public static void validateNumeric(String dateOfVisit) {
        try {
            TypeValidator.checkIsNumeric(dateOfVisit);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_OF_VISIT_INVALID.getMessage());
        }
    }

    public static void validateRange(int dateOfVisit) {
        checkStart(dateOfVisit);
        checkEnd(dateOfVisit);
    }

    private static void checkStart(int dateOfVisit) {
        if (dateOfVisit < EVENT_DAY_START.getDate()) {
            throw new IllegalArgumentException(DATE_OF_VISIT_INVALID.getMessage());
        }
    }

    private static void checkEnd(int dateOfVisit) {
        if (dateOfVisit > EVENT_DAY_END.getDate()) {
            throw new IllegalArgumentException(DATE_OF_VISIT_INVALID.getMessage());
        }
    }
}