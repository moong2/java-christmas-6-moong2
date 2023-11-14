package christmas.model.util.event;

public enum EventDetails {
    EVENT_STANDARD(10_000),
    GIFT_EVENT_STANDARD(120_000),
    
    WEEKEND_WEEKDAY_EVENT_DISCOUNT(2023),
    CHRISTMAS_EVENT_BASE(1000),
    CHRISTMAS_EVENT_DIFF(100),
    SPECIAL_EVENT_DISCOUNT(1000),
    NONE_DISCOUNT(0),
    
    COUNT_OF_GIVE_MENU(1),

    APPLY_DISCOUNT(-1);

    private final int details;

    EventDetails(int details) {
        this.details = details;
    }

    public int getDetails() {
        return details;
    }
    
    public static int christmasEventDiscount(int day) {
        return CHRISTMAS_EVENT_BASE.details + (day - 1) * CHRISTMAS_EVENT_DIFF.details;
    }
    
    public static int weekendOrWeekdayEventDiscount(int numberOfFood) {
        return numberOfFood * WEEKEND_WEEKDAY_EVENT_DISCOUNT.details;
    }
}
