package christmas.model.domain;

import static christmas.model.calculator.OrderCalculator.totalOrderAmountBeforeDiscount;

import java.util.Map;

/**
 * Client의 주문 내역을 담는 객체이다. 메뉴 이름과 주문한 메뉴의 개수를 Map으로 캡슐화한다. 주문한 메뉴 이름과 메뉴 개수를 기준으로 할인 전 총 주문 금액을 계산하는 기능을 제공한다.
 */
public class Orders {
    private final Map<String, Integer> orders;

    private Orders(Map<String, Integer> orders) {
        this.orders = orders;
    }

    /**
     * Orders 객체를 생성하는 팩토리 메소드이다.
     *
     * @param orders 주문 내역
     * @return 새 Orders 객체
     */
    public static Orders of(Map<String, Integer> orders) {
        return new Orders(orders);
    }

    /**
     * 주문 내역인 Map을 반환한다.
     *
     * @return 메뉴 이름을 String으로, 주문 개수를 Integer로 가지고 있는 Map
     */
    public Map<String, Integer> getOrders() {
        return orders;
    }

    /**
     * 할인 전 총 주문금액을 계산한다. 계산을 OrderCalculator에게 위임한다.
     *
     * @return integer 형식의 할인 전 총 주문 금액
     */
    public int calculateTotalAmountBeforeDiscount() {
        return totalOrderAmountBeforeDiscount(orders);
    }
}
