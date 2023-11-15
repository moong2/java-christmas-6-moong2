package christmas.service;

import static christmas.model.validator.DateOfVisitValidator.validateNumeric;
import static christmas.model.validator.DateOfVisitValidator.validateRange;
import static christmas.model.validator.OrderingValidator.validateInMenuList;
import static christmas.model.validator.OrderingValidator.validateNotOnlyBeverage;
import static christmas.model.validator.OrderingValidator.validateNumberOfFoodsInRange;
import static christmas.model.validator.OrderingValidator.validateTotalNumberOfFoodsInRange;
import static christmas.util.exceptions.Exceptions.DATE_OF_VISIT_INVALID;
import static christmas.util.exceptions.Exceptions.ORDERING_INVALID;

import christmas.model.converter.StringToInteger;
import christmas.model.converter.StringToMap;
import java.util.Map;

/**
 * InputValidateService는 입력값에 대한 검증을 하는 클래스다. 방문 날짜와 주문 메뉴가 올바른 데이터 형식임을 보증한다.
 */
public class InputValidateService {
    /**
     * 문자열로 제공된 방문 날짜를 검증하고 integer로 변환한다. 방문 날짜가 숫자인지 검증 후 변환하고, 이벤트 내의 날짜인지 검증한다.
     *
     * @param visitDate 방문 날짜
     * @return integer 형식의 방문 날짜
     */
    public int validateAndConvertVisitDate(String visitDate) {
        validateNumeric(visitDate);
        int convertedVisitDate = convertToInteger(visitDate);
        validateRange(convertedVisitDate);

        return convertedVisitDate;
    }

    /**
     * 문자열로 제공된 주문 내역을 검증하고 메뉴 이름과 주문 메뉴 개수를 가진 Map으로 변환한다. 메뉴가 메뉴판에 있는지, 주문 메뉴 개수와 총 주문 메뉴 개수가 범위 내인지, 음료만 시킨 것은 아닌지
     * 검증한다.
     *
     * @param orders 주문 내역
     * @return 메뉴 이름과 주문 메뉴 개수를 가진 Map
     */
    public Map<String, Integer> validateAndConvertOrders(String orders) {
        Map<String, Integer> convertedOrders = convertToMap(orders);
        validateInMenuList(convertedOrders);
        validateNumberOfFoodsInRange(convertedOrders);
        validateNotOnlyBeverage(convertedOrders);
        validateTotalNumberOfFoodsInRange(convertedOrders);

        return convertedOrders;
    }

    private int convertToInteger(String visitDate) {
        try {
            return new StringToInteger().toType(visitDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_OF_VISIT_INVALID.getMessage());
        }
    }

    private Map<String, Integer> convertToMap(String orders) {
        try {
            return new StringToMap().toType(orders);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
        }
    }
}
