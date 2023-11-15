package christmas.model.calculator;

import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * OrderCalculator는 Client가 주문한 메뉴들에 근거하여 금액을 계산하는 책임이 있다. 할인 전, 후에 대한 결제 금액을 계산하고 총 할인가를 도출해낸다.
 */
public class OrderCalculator {
    /**
     * 주어진 메뉴를 기반으로 할인 전 총 주문 금액을 계산한다. 각각의 주문에 대한 메뉴 가격과 주문 수를 곱한 값을 더해 반환한다.
     *
     * @param orders 주문 메뉴
     * @return integer 형식의 할인 전 총 주문 금액
     */
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

    /**
     * 주어진 할인 내역을 기반으로 총혜택금액 계산한다. 각각의 할인 내역을 더해 반환한다.
     *
     * @param discounts 할인 이벤트인 EventCategory와 해당 이벤트에서 받은 할인가인 Integer를 가지고 있는 Map
     * @return integer 형식의 총혜택금액
     */
    public static int totalDiscount(Map<EventCategory, Integer> discounts) {
        return discounts.entrySet()
                .stream()
                .mapToInt((discount) -> discount.getValue())
                .sum();
    }

    /**
     * 주어진 할인 전 총 주문 금액과 할인금을 기반으로 할인 후 예상 결제 금액을 계산한다. 할인 전 총 주문 금액에서 할인금을 뺀 값을 반환한다.
     *
     * @param totalOrderAmountBeforeDiscount 할인 전 총 주문 금액
     * @param discount                       할인금
     * @return integer 형식의 할인 후 예상 결제 금액
     */
    public static int totalOrderAmountAfterDiscount(int totalOrderAmountBeforeDiscount, int discount) {
        return totalOrderAmountBeforeDiscount - discount;
    }
}
