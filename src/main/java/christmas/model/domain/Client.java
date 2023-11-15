package christmas.model.domain;

import christmas.model.util.menu.MenuList;
import christmas.model.util.user.UserRoles;

public class Client {
    private final VisitInformation visitInformation;
    private final Role role;

    private Client(VisitInformation visitInformation, Role role) {
        this.visitInformation = visitInformation;
        this.role = role;
    }

    public static Client of(VisitInformation visitInformation) {
        return new Client(visitInformation, new Role(UserRoles.CLIENT));
    }

    public VisitInformation getVisitInformation() {
        return this.visitInformation;
    }

    public int getTotalAmountBeforeDiscount() {
        return this.visitInformation.getTotalAmountBeforeDiscount();
    }

    public MenuList getGivenMenu() {
        return this.visitInformation.getGivenMenu();
    }

    public int getChristmasDDayDiscount() {
        return this.visitInformation.getChristmasDDayDiscount();
    }

    public int getWeekdayDiscount() {
        return this.visitInformation.getWeekdayDiscount();
    }

    public int getWeekendDiscount() {
        return this.visitInformation.getWeekendDiscount();
    }

    public int getSpecialDiscount() {
        return this.visitInformation.getSpecialDiscount();
    }
}
