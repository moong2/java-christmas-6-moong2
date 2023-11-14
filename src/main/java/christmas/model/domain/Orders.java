package christmas.model.domain;

import static christmas.model.calculator.OrderCalculator.totalOrderAmountBeforeDiscount;

import java.util.Map;

public class Orders {
    private final Map<String, Integer> orders;

    public Orders(Map<String, Integer> orders) {
        this.orders = orders;
    }

    public int calculateTotalAmountBeforeDiscount() {
        return totalOrderAmountBeforeDiscount(orders);
    }

    public Map<String, Integer> getOrders() {
        return orders;
    }
}
