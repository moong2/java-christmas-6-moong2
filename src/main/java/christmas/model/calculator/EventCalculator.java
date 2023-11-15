package christmas.model.calculator;

import static christmas.model.util.event.EventDetails.GIFT_EVENT_STANDARD;
import static christmas.model.util.event.EventDetails.NONE_DISCOUNT;
import static christmas.model.util.event.EventDetails.christmasEventDiscount;
import static christmas.model.util.event.EventPeriod.isBeforeChristmas;
import static christmas.model.util.event.SpecialEvent.specialEventDiscount;
import static christmas.model.util.menu.MenuList.CHAMPAGNE;
import static christmas.model.util.menu.MenuList.NONE_MENU;

import christmas.model.util.event.EventDetails;
import christmas.model.util.menu.MenuCategory;
import christmas.model.util.menu.MenuList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

/**
 * EventCalculator는 이벤트에 관련된 항목에 대한 계산을 하는 책임이 있다. 증정 메뉴와 할인, 혜택과 관련된 금액을 계산한다.
 */
public class EventCalculator {

    /**
     * 주어진 할인 전 총주문 금액을 기반으로 증정 메뉴를 결정한다.
     *
     * @param totalOrderAmountBeforeDiscount 할인 전 총주문 금액
     * @return 일정 기준 이상일 때 샴페인을 증정하며, 그 외엔 증정하지 않는다.
     */
    public static MenuList givenMenu(int totalOrderAmountBeforeDiscount) {
        if (totalOrderAmountBeforeDiscount >= GIFT_EVENT_STANDARD.getDetails()) {
            return CHAMPAGNE;
        }
        return NONE_MENU;
    }

    /**
     * 주어진 날짜를 기준으로 크리스마스 디데이 할인가를 계산한다.
     *
     * @param date 주어진 날짜
     * @return 크리스마스 디데이 할인가
     */
    public static int christmasDDayEvent(LocalDate date) {
        int day = date.getDayOfMonth();

        if (isBeforeChristmas(day)) {
            return christmasEventDiscount(day);
        }
        return NONE_DISCOUNT.getDetails();
    }

    /**
     * 주어진 주문 메뉴와 방문 날짜를 기준으로 평일 이벤트 할인가를 계산한다. 할인가는 디저트 메뉴에 적용된다.
     *
     * @param orders 주문 메뉴
     * @param date   방문 날짜
     * @return 평일 이벤트 할인가
     */
    public static int weekdayEvent(Map<String, Integer> orders, LocalDate date) {
        if (isWeekday(date)) {
            return orders.entrySet()
                    .stream()
                    .filter(order -> MenuList.getMenuByName(order.getKey()).getCategory() == MenuCategory.DESSERT)
                    .mapToInt(Map.Entry::getValue)
                    .map(EventDetails::weekendOrWeekdayEventDiscount)
                    .sum();
        }

        return NONE_DISCOUNT.getDetails();
    }

    /**
     * 주어진 주문 메뉴와 방문 날짜를 기준으로 주말 이벤트 할인가를 계산한다. 할인가는 메인 메뉴에 적용된다.
     *
     * @param orders 주문 메뉴
     * @param date   방문 날짜
     * @return 주말 이벤트 할인가
     */
    public static int weekendEvent(Map<String, Integer> orders, LocalDate date) {
        if (isWeekend(date)) {
            return orders.entrySet()
                    .stream()
                    .filter(order -> MenuList.getMenuByName(order.getKey()).getCategory() == MenuCategory.MAIN)
                    .mapToInt(Map.Entry::getValue)
                    .map(EventDetails::weekendOrWeekdayEventDiscount)
                    .sum();
        }

        return NONE_DISCOUNT.getDetails();
    }

    /**
     * 주어진 날짜를 기준으로 특별 이벤트 할인가를 계산한다. 달력에 별이 있는 날짜에 특별 이벤트 할인가가 적용된다.
     *
     * @param date 방문 날짜
     * @return 특별 이벤트 할인가
     */
    public static int specialEvent(LocalDate date) {
        int day = date.getDayOfMonth();

        return specialEventDiscount(day);
    }

    private static boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();

        return (day != DayOfWeek.FRIDAY && day != DayOfWeek.SATURDAY);
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();

        return (day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY);
    }
}
