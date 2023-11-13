package christmas.model.util.event;

public enum EventDetails {
    GIFT_EVENT_STANDARD(120_000),
    
    WEEKEND_WEEKDAY_EVENT_DISCOUNT(2023),
    CHRISTMAS_EVENT_BASE(1000),
    CHRISTMAS_EVENT_DIFF(100),
    SPECIAL_EVENT_DISCOUNT(1000),
    NONE_DISCOUNT(0);

    private final int money;

    EventDetails(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
    
    public static int christmasEventDiscount(int day) {
        return CHRISTMAS_EVENT_BASE.money + (day - 1) * CHRISTMAS_EVENT_DIFF.money;
    }
    
    public static int weekendOrWeekdayEventDiscount(int numberOfFood) {
        return numberOfFood * WEEKEND_WEEKDAY_EVENT_DISCOUNT.money;
    }
}
