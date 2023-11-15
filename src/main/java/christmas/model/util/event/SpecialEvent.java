package christmas.model.util.event;

import static christmas.model.util.event.EventDetails.NONE_DISCOUNT;
import static christmas.model.util.event.EventDetails.SPECIAL_EVENT_DISCOUNT;

/**
 * 특별 이벤트 할인에 대한 정보를 나타내는 enum이다.
 */
public enum SpecialEvent {
    STAR_DAY_3RD(3),
    STAR_DAY_10TH(10),
    STAR_DAY_17TH(17),
    STAR_DAY_24TH(24),
    STAR_DAY_25TH(25),
    STAR_DAY_31TH(31);

    private final int day;

    /**
     * SpecialEvent의 생성자다.
     *
     * @param day 특별 이벤트가 적용된 날짜
     */
    SpecialEvent(int day) {
        this.day = day;
    }

    /**
     * 입력된 날짜를 기준으로 특별 이벤트 할인을 받을 수 있는지 검증하고, 특별 이벤트 할인가를 반환한다.
     *
     * @param day 방문 날짜
     * @return 특별 이벤트 데이라면 특별 이벤트 할인가를 반환하고, 특별 이벤트 데이가 아니라면 0원을 반환한다.
     */
    public static int specialEventDiscount(int day) {
        for (SpecialEvent specialDay : SpecialEvent.values()) {
            if (specialDay.day == day) {
                return SPECIAL_EVENT_DISCOUNT.getDetails();
            }
        }
        return NONE_DISCOUNT.getDetails();
    }
}
