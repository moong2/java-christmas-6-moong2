package christmas.model.util.event;

public enum EventCategory {
    CHRISTMAS_DDAY_EVENT("크리스마스 디데이 할인"),
    WEEKDAY_EVENT("평일 할인"),
    WEEKEND_EVENT("주말 할인"),
    SPECIAL_EVENT("특별 할인"),
    GIVE_EVENT("증정 이벤트");

    private final String category;

    EventCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
