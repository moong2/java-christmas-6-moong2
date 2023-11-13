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

public class eventCalculator {
    public static MenuList givenMenu(int totalOrderAmountBeforeDiscount) {
        if (totalOrderAmountBeforeDiscount >= GIFT_EVENT_STANDARD.getMoney()) {
            return CHAMPAGNE;
        }
        return NONE_MENU;
    }

    public static int christmasDDayEvent(LocalDate date) {
        int day = date.getDayOfMonth();

        if (isBeforeChristmas(day)) {
            return christmasEventDiscount(day);
        }
        return NONE_DISCOUNT.getMoney();
    }

    public static int weekdayEvent(Map<String, Integer> orders, LocalDate date) {
        if (isWeekday(date)) {
            return orders.entrySet()
                    .stream()
                    .filter(order -> MenuList.getMenuByName(order.getKey()).getCategory() == MenuCategory.DESSERT)
                    .mapToInt(Map.Entry::getValue)
                    .map(EventDetails::weekendOrWeekdayEventDiscount)
                    .sum();
        }
        
        return NONE_DISCOUNT.getMoney();
    }

    private static boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();

        return (day != DayOfWeek.FRIDAY && day != DayOfWeek.SATURDAY);
    }

    public static int weekendEvent(Map<String, Integer> orders, LocalDate date) {
        if (isWeekend(date)) {
            return orders.entrySet()
                    .stream()
                    .filter(order -> MenuList.getMenuByName(order.getKey()).getCategory() == MenuCategory.MAIN)
                    .mapToInt(Map.Entry::getValue)
                    .map(EventDetails::weekendOrWeekdayEventDiscount)
                    .sum();
        }
        
        return NONE_DISCOUNT.getMoney();
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();

        return (day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY);
    }

    public static int specialEvent(LocalDate date) {
        int day = date.getDayOfMonth();

        return specialEventDiscount(day);
    }
}
