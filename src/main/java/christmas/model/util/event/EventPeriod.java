package christmas.model.util.event;

/**
 * 이벤트 기간에 대한 정보를 가지고 있는 enum이다.
 */
public enum EventPeriod {
    EVENT_YEAR(2023),
    EVENT_MONTH(12),

    EVENT_DAY_START(1),
    EVENT_DAY_CHRISTMAS(25),
    EVENT_DAY_END(31);

    private final int date;

    /**
     * EventPeriod의 생성자다.
     *
     * @param date 이벤트 기간에 대한 정보
     */
    EventPeriod(int date) {
        this.date = date;
    }

    /**
     * 이벤트 기간에 대한 정보를 반환한다.
     *
     * @return 이벤트 기간에 대한 정보
     */
    public int getDate() {
        return date;
    }

    /**
     * 입력된 날짜를 기준으로 크리스마스 이전인지 확인한다.
     *
     * @param day 방문 날짜
     * @return 크리스마스 당일을 포함하여 크리스마스 이전이라면 true를, 이후라면 false를 반환한다.
     */
    public static boolean isBeforeChristmas(int day) {
        return day <= EVENT_DAY_CHRISTMAS.getDate();
    }
}
