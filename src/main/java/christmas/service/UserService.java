package christmas.service;

import static christmas.model.util.event.EventPeriod.EVENT_MONTH;
import static christmas.model.util.event.EventPeriod.EVENT_YEAR;
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
import christmas.model.domain.Client;
import christmas.model.domain.Orders;
import christmas.model.domain.Users;
import christmas.model.domain.VisitInformation;
import java.time.LocalDate;
import java.util.Map;

public class UserService {
    private final Users users = Users.getInstance();

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

    public VisitInformation createVisitInformation(int visitDate, Map<String, Integer> orders) {
        return new VisitInformation(
                LocalDate.of(EVENT_YEAR.getDate(), EVENT_MONTH.getDate(), visitDate),
                new Orders(orders)
        );
    }

    public Client saveUser(VisitInformation visitInformation) {
        Client client = Client.of(visitInformation);
        users.addUser(client);

        return client;
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
