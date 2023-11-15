package christmas.model.validator;

import static christmas.model.util.event.EventPeriod.EVENT_DAY_END;
import static christmas.model.util.event.EventPeriod.EVENT_DAY_START;
import static christmas.util.exceptions.Exceptions.DATE_OF_VISIT_INVALID;

/**
 * DateOfVisitValidator는 방문할 날짜를 검증하는 static 메소드를 가지고 있다. 방문할 날짜가 숫자인지, 숫자라면 이벤트 기간 내에 속하는지 확인한다.
 */
public class DateOfVisitValidator {

    /**
     * 입력받은 문자열 형식의 방문 날짜가 숫자인지 확인한다. 숫자가 아니라면 IllegalArgumentException을 던진다.
     *
     * @param dateOfVisit 문자열 형식의 방문 날짜
     * @throws IllegalArgumentException 방문 날짜가 숫자가 아닌 경우
     */
    public static void validateNumeric(String dateOfVisit) {
        if (!dateOfVisit.matches("-?\\d+")) {
            throw new NumberFormatException(DATE_OF_VISIT_INVALID.getMessage());
        }
    }

    /**
     * 방문 날짜가 이벤트 기간 내에 속하는지 확인한다. 이벤트 기간 내가 아닐 경우 예외를 던진다.
     *
     * @param dateOfVisit 방문 날짜
     * @throws IllegalArgumentException 이벤트 기간이 아닐 경우
     */
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