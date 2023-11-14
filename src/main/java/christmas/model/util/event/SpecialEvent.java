package christmas.model.util.event;

import static christmas.model.util.event.EventDetails.NONE_DISCOUNT;
import static christmas.model.util.event.EventDetails.SPECIAL_EVENT_DISCOUNT;

public enum SpecialEvent {
    STAR_DAY_3RD(3),
    STAR_DAY_10TH(10),
    STAR_DAY_17TH(17),
    STAR_DAY_24TH(24),
    STAR_DAY_25TH(25),
    STAR_DAY_31TH(31);

    private final int day;

    SpecialEvent(int day) {
        this.day = day;
    }

    public static int specialEventDiscount(int day) {
        for (SpecialEvent specialDay : SpecialEvent.values()) {
            if (specialDay.day == day) {
                return SPECIAL_EVENT_DISCOUNT.getDetails();
            }
        }
        return NONE_DISCOUNT.getDetails();
    }
}
