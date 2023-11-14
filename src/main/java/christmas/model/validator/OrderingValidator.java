package christmas.model.validator;

import static christmas.util.exceptions.Exceptions.ORDERING_INVALID;
import static christmas.model.util.menu.MenuCategory.BEVERAGE;
import static christmas.model.util.menu.MenuDetails.checkMaximumOfTotalNumberOfFood;
import static christmas.model.util.menu.MenuDetails.checkMinimumOfTotalNumberOfFood;

import christmas.model.util.menu.MenuList;
import java.util.Map;

public class OrderingValidator {
    public static void validateInMenuList(Map<String, Integer> orders) {
        Map<String, Integer> menuList = MenuList.getAllMenus();

        for (String menuName : orders.keySet()) {
            if (!menuList.containsKey(menuName)) {
                throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
            }
        }
    }

    public static void validateNumberOfFoodsInRange(Map<String, Integer> orders) {
        for (Integer numberOfFood : orders.values()) {
            checkMinimumOfTotalNumberOfFood(numberOfFood);
            checkMaximumOfTotalNumberOfFood(numberOfFood);
        }
    }

    public static void validateNotOnlyBeverage(Map<String, Integer> orders) {
        Map<String, Integer> beverageMenus = MenuList.getCategoryMenus(BEVERAGE);

        boolean isOnlyBeverage = orders.keySet().stream()
                .allMatch(beverageMenus::containsKey);

        if (isOnlyBeverage) {
            throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
        }
    }

    public static void validateTotalNumberOfFoodsInRange(Map<String, Integer> orders) {
        int totalNumberOfFoods = orders.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        checkMinimumOfTotalNumberOfFood(totalNumberOfFoods);
        checkMaximumOfTotalNumberOfFood(totalNumberOfFoods);
    }
}
