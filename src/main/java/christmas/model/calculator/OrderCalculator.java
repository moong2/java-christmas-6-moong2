package christmas.model.calculator;

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

            Integer numberOfFood = order.getValue();
            totalOrderAmountBeforeDiscount += (menu.getPrice() * numberOfFood);
        }

        return totalOrderAmountBeforeDiscount;
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
