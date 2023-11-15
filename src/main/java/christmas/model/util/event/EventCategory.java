package christmas.model.util.event;

/**
 * 이벤트의 종류를 나타내는 enum이다.
 */
public enum EventCategory {
    CHRISTMAS_DDAY_EVENT("크리스마스 디데이 할인"),
    WEEKDAY_EVENT("평일 할인"),
    WEEKEND_EVENT("주말 할인"),
    SPECIAL_EVENT("특별 할인"),
    GIVE_EVENT("증정 이벤트");

    private final String category;

    /**
     * EventCategory의 생성자다.
     *
     * @param category 이벤트의 이름
     */
    EventCategory(String category) {
        this.category = category;
    }

    /**
     * 이벤트의 이름을 반환한다.
     *
     * @return 이벤트 이름
     */
    public String getCategory() {
        return category;
    }
}
