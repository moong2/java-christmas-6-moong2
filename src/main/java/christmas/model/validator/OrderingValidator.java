package christmas.model.validator;

import static christmas.util.exceptions.Exceptions.ORDERING_INVALID;
import static christmas.util.menu.MenuCategory.BEVERAGE;
import static christmas.util.menu.MenuDetails.checkMaximumOfTotalNumberOfFood;
import static christmas.util.menu.MenuDetails.checkMinimumOfTotalNumberOfFood;

import christmas.util.menu.MenuUtils;
import java.util.Map;

public class OrderingValidator {
    public void validateInMenuList(Map<String, Integer> orders) {
        Map<String, Integer> menuList = MenuUtils.getAllMenus();

        for (String menuName : orders.keySet()) {
            if (!menuList.containsKey(menuName)) {
                throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
            }
        }
    }

    public void validateNumberOfFoodsInRange(Map<String, Integer> orders) {
        for (Integer numberOfFood : orders.values()) {
            checkMinimumOfTotalNumberOfFood(numberOfFood);
            checkMaximumOfTotalNumberOfFood(numberOfFood);
        }
    }

    public void validateNotOnlyBeverage(Map<String, Integer> orders) {
        Map<String, Integer> beverageMenus = MenuUtils.getCategoryMenus(BEVERAGE);

        boolean isOnlyBeverage = orders.keySet().stream()
                .allMatch(beverageMenus::containsKey);

        if (isOnlyBeverage) {
            throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
        }
    }

    public void validateTotalNumberOfFoodsInRange(Map<String, Integer> orders) {
        int totalNumberOfFoods = orders.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        checkMinimumOfTotalNumberOfFood(totalNumberOfFoods);
        checkMaximumOfTotalNumberOfFood(totalNumberOfFoods);
    }
}
