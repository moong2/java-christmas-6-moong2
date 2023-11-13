package christmas.util.calendar;

public enum EventDetails {
    GIFT_MENU_EVENT(120_000);

    private final int money;

    EventDetails(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
