package christmas.model.domain;

import static christmas.model.calculator.EventCalculator.christmasDDayEvent;
import static christmas.model.calculator.EventCalculator.givenMenu;
import static christmas.model.calculator.EventCalculator.specialEvent;
import static christmas.model.calculator.EventCalculator.weekdayEvent;
import static christmas.model.calculator.EventCalculator.weekendEvent;

import christmas.model.util.menu.MenuList;
import java.time.LocalDate;

public class VisitInformation {
    private final LocalDate visitDate;
    private final Orders orders;

    public VisitInformation(LocalDate visitDate, Orders orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }

    public int getTotalAmountBeforeDiscount() {
        return orders.calculateTotalAmountBeforeDiscount();
    }

    public MenuList getGivenMenu() {
        int totalAmountBeforeDiscount = this.getTotalAmountBeforeDiscount();

        return givenMenu(totalAmountBeforeDiscount);
    }

    public int getChristmasDDayDiscount() {
        return christmasDDayEvent(visitDate);
    }

    public int getWeekdayDiscount() {
        return weekdayEvent(orders.getOrders(), visitDate);
    }

    public int getWeekendDiscount() {
        return weekendEvent(orders.getOrders(), visitDate);
    }

    public int getSpecialDiscount() {
        return specialEvent(visitDate);
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public Orders getOrders() {
        return orders;
    }
}
