package christmas.model.validator;

import static christmas.util.exceptions.Exceptions.ORDERING_INVALID;
import static christmas.model.util.menu.MenuCategory.BEVERAGE;
import static christmas.model.util.menu.MenuDetails.checkMaximumOfTotalNumberOfFood;
import static christmas.model.util.menu.MenuDetails.checkMinimumOfTotalNumberOfFood;

import christmas.model.util.menu.MenuList;
import java.util.Map;

/**
 * OrderingValidator는 입력 받은 주문 내역에 대한 검증을 하는 static 메소드를 제공한다. 주문 메뉴가 메뉴판에 있는 메뉴인지, 주문 메뉴의 개수가 범위 내인지, 음료만 시켰는지 여부를
 * 검증한다.
 */
public class OrderingValidator {
    /**
     * 주문 내역의 메뉴들이 메뉴판에 속한 메뉴인지 확인한다. 메뉴판에 속하지 않을 경우, IllegalArgumentException을 던진다.
     *
     * @param orders 메뉴 이름을 key로, 주문 메뉴의 개수를 value로 가지는 map을 가지는 객체
     * @throws IllegalArgumentException 메뉴판에 속하지 않은 메뉴를 주문한 경우
     */
    public static void validateInMenuList(Map<String, Integer> orders) {
        Map<String, Integer> menuList = MenuList.getAllMenus();

        for (String menuName : orders.keySet()) {
            if (!menuList.containsKey(menuName)) {
                throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
            }
        }
    }

    /**
     * 주문 메뉴의 개수가 범위 내에 속하는지 검증한다. 범위 내에 속하지 않을 경우 예외를 던진다.
     *
     * @param orders 메뉴 이름을 key로, 주문 메뉴의 개수를 value로 가지는 map을 가지는 객체
     * @throws IllegalArgumentException 주문 메뉴의 개수가 범위 내에 속하지 않는 경우
     */
    public static void validateNumberOfFoodsInRange(Map<String, Integer> orders) {
        for (Integer numberOfFood : orders.values()) {
            checkMinimumOfTotalNumberOfFood(numberOfFood);
            checkMaximumOfTotalNumberOfFood(numberOfFood);
        }
    }

    /**
     * 음료만 시켰는지 검증한다. 음료만 시켰을 경우 예외를 던진다.
     *
     * @param orders 메뉴 이름을 key로, 주문 메뉴의 개수를 value로 가지는 map을 가지는 객체
     * @throws IllegalArgumentException 음료만 주문한 경우
     */
    public static void validateNotOnlyBeverage(Map<String, Integer> orders) {
        Map<String, Integer> beverageMenus = MenuList.getCategoryMenus(BEVERAGE);

        boolean isOnlyBeverage = orders.keySet().stream()
                .allMatch(beverageMenus::containsKey);

        if (isOnlyBeverage) {
            throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
        }
    }

    /**
     * 주문한 전체 메뉴의 개수가 범위 내에 속하는지 확인한다. 범위 내에 속하지 않을 경우 IllegalArgumentException을 던진다.
     *
     * @param orders 메뉴 이름을 key로, 주문 메뉴의 개수를 value로 가지는 map을 가지는 객체
     * @throws IllegalArgumentException 주문한 총 메뉴 개수가 범위 내에 속하지 않는 경우
     */
    public static void validateTotalNumberOfFoodsInRange(Map<String, Integer> orders) {
        int totalNumberOfFoods = orders.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        checkMinimumOfTotalNumberOfFood(totalNumberOfFoods);
        checkMaximumOfTotalNumberOfFood(totalNumberOfFoods);
    }
}
