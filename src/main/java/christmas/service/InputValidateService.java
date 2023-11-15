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

public class InputValidateService {
    public int validateAndConvertVisitDate(String visitDate) {
        validateNumeric(visitDate);
        int convertedVisitDate = convertToInteger(visitDate);
        validateRange(convertedVisitDate);

        return convertedVisitDate;
    }

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
