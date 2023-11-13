package christmas.model.calculator;

import static christmas.util.exceptions.Exceptions.ORDERING_INVALID;
import static christmas.model.util.menu.MenuList.NONE_MENU;

import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import java.util.Map;
import java.util.Map.Entry;

public class OrderCalculator {
    public static int totalOrderAmountBeforeDiscount(Map<String, Integer> orders) {
        int totalOrderAmountBeforeDiscount = 0;

        for (Entry<String, Integer> order : orders.entrySet()) {
            String menuName = order.getKey();
            MenuList menu = MenuList.getMenuByName(menuName);

            menuCheck(menu);

            Integer numberOfFood = order.getValue();
            totalOrderAmountBeforeDiscount += (menu.getPrice() * numberOfFood);
        }

        return totalOrderAmountBeforeDiscount;
    }

    private static void menuCheck(MenuList menu) {
        if (menu == NONE_MENU) {
            throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
        }
    }

    public static int totalDiscount(Map<EventCategory, Integer> discounts) {
        return discounts.entrySet()
                .stream()
                .mapToInt((discount) -> discount.getValue())
                .sum();
    }

    public static int totalOrderAmountAfterDiscount(int totalOrderAmountBeforeDiscount, int discount) {
        return totalOrderAmountBeforeDiscount - discount;
    }
}
