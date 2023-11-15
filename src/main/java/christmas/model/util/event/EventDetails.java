package christmas.model.util.event;

/**
 * 각각의 이벤트에 대한 기준 금액이나 계산 방법을 나타내는 enum이다.
 */
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

    /**
     * EventDetails의 생성자다.
     *
     * @param details 이벤트에 대한 기준 금액 혹은 게산 방법
     */
    EventDetails(int details) {
        this.details = details;
    }

    /**
     * 이벤트에 대한 기준 금액 혹은 계산 방법을 반환한다.
     *
     * @return 이벤트에 대한 기준 금액 혹은 계산 방법
     */
    public int getDetails() {
        return details;
    }

    /**
     * 크리스마스 디데이 이벤트 할인가를 계산한다.
     * 12월 1일에 1000원으로 시작하여 매일 100원씩 증가한다.
     *
     * @param day 방문 날짜
     * @return 크리스마스 디데이 이벤트 할인가
     */
    public static int christmasEventDiscount(int day) {
        return CHRISTMAS_EVENT_BASE.details + (day - 1) * CHRISTMAS_EVENT_DIFF.details;
    }

    /**
     * 평일 혹은 주말 이벤트 할인가를 계산한다.
     * 주문 메뉴의 개수에 할인가를 곱한 것을 반환한다.
     *
     * @param numberOfFood 주문 메뉴의 개수
     * @return 평일 혹은 주말 이벤트 할인가
     */
    public static int weekendOrWeekdayEventDiscount(int numberOfFood) {
        return numberOfFood * WEEKEND_WEEKDAY_EVENT_DISCOUNT.details;
    }
}
