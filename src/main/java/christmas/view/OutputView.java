package christmas.view;

import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import java.util.Map;

public interface OutputView {
    void printPrecaution();

    void printGuidance(String message);

    void printPreview(int visitDate);

    void printOrderMenus(Map<String, Integer> orders);

    void printTotalAmountBeforeDiscount(int totalAmountBeforeDiscount);

    void printGivenMenu(MenuList menuList);

    void printBenefits(Map<EventCategory, Integer> benefits);

    void printTotalBenefitsAmount(int totalBenefitsAmount);

    void printTotalAmountAfterDiscount(int totalAmountAfterDiscount);

    void printBadge(BadgeCategory badge);

    void printEventNotApply(String guidance);

    void printReport(String message);

    void printNumberOfClients(int totalNumberOfClients);

    void printNumberOfNextClients(double totalNumberOfNextClients);

    void printTotalAmountOfOrders(long totalAmountOfOrders);

    void printException(String message);

}
