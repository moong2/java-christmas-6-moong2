package christmas.model.calculator;

import static christmas.util.calendar.EventDetails.GIFT_MENU_EVENT;
import static christmas.util.menu.MenuList.CHAMPAGNE;
import static christmas.util.menu.MenuList.NONE_MENU;

import christmas.util.menu.MenuList;

public class eventCalculator {
    public static MenuList givenMenu(int totalOrderAmountBeforeDiscount) {
        if (totalOrderAmountBeforeDiscount >= GIFT_MENU_EVENT.getMoney()) {
            return CHAMPAGNE;
        }
        return NONE_MENU;
    }
}
