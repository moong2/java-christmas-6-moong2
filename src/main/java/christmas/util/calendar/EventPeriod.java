package christmas.util.calendar;

public enum EventPeriod {
    EVENT_START(1),
    EVENT_END(31);

    private final int date;

    EventPeriod(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}
