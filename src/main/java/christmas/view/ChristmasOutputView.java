package christmas.view;

import static christmas.model.util.event.EventDetails.APPLY_DISCOUNT;
import static christmas.model.util.event.EventDetails.COUNT_OF_GIVE_MENU;
import static christmas.model.util.event.EventPeriod.EVENT_MONTH;
import static christmas.util.instructions.Guidance.PREVIEW_BENEFITS;
import static christmas.util.instructions.Guidance.PREVIEW_EACH_BENEFITS;
import static christmas.util.instructions.Guidance.PREVIEW_EACH_MENU;
import static christmas.util.instructions.Guidance.PREVIEW_EVENT_BADGE;
import static christmas.util.instructions.Guidance.PREVIEW_EVENT_NOT_APPLY;
import static christmas.util.instructions.Guidance.PREVIEW_GIVE_MENU;
import static christmas.util.instructions.Guidance.PREVIEW_GIVE_MENU_EXISTS;
import static christmas.util.instructions.Guidance.PREVIEW_MONEY;
import static christmas.util.instructions.Guidance.PREVIEW_ORDER_MENU;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_AMOUNT_AFTER_DISCOUNT;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_AMOUNT_BEFORE_DISCOUNT;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS_AMOUNT;

import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import christmas.util.instructions.Precautions;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Map.Entry;

public class ChristmasOutputView implements OutputView {
    @Override
    public void printPrecaution() {
        System.out.println(Precautions.getPrecautions());
    }

    @Override
    public void printGuidance(String message) {
        System.out.println(message);
    }

    @Override
    public void printPreview(int visitDate) {
        this.printGuidance(String.format(PREVIEW_BENEFITS.getGuidance(), EVENT_MONTH.getDate(), visitDate));
        System.out.println();
    }

    @Override
    public void printOrderMenus(Map<String, Integer> orders) {
        this.printGuidance(PREVIEW_ORDER_MENU.getGuidance());
        for (Entry<String, Integer> order : orders.entrySet()) {
            System.out.println(String.format(PREVIEW_EACH_MENU.getGuidance(), order.getKey(), order.getValue()));
        }
        System.out.println();
    }

    @Override
    public void printTotalAmountBeforeDiscount(int totalAmountBeforeDiscount) {
        this.printGuidance(PREVIEW_TOTAL_AMOUNT_BEFORE_DISCOUNT.getGuidance());
        System.out.println(String.format(PREVIEW_MONEY.getGuidance(), formatCurrency(totalAmountBeforeDiscount)));
        System.out.println();
    }

    @Override
    public void printGivenMenu(MenuList menuList) {
        this.printGuidance(PREVIEW_GIVE_MENU.getGuidance());
        System.out.println(String.format(PREVIEW_GIVE_MENU_EXISTS.getGuidance(), menuList.getMenuName(),
                COUNT_OF_GIVE_MENU.getDetails()));
        System.out.println();
    }

    @Override
    public void printBenefits(Map<EventCategory, Integer> benefits) {
        this.printGuidance(PREVIEW_TOTAL_BENEFITS.getGuidance());
        for (Entry<EventCategory, Integer> benefit : benefits.entrySet()) {
            System.out.println(String.format(PREVIEW_EACH_BENEFITS.getGuidance(), benefit.getKey().getCategory(),
                    formatCurrency(benefit.getValue() *APPLY_DISCOUNT.getDetails())));
        }
        System.out.println();
    }

    @Override
    public void printTotalBenefitsAmount(int totalBenefitsAmount) {
        this.printGuidance(PREVIEW_TOTAL_BENEFITS_AMOUNT.getGuidance());
        System.out.println(String.format(PREVIEW_MONEY.getGuidance(),
                formatCurrency(totalBenefitsAmount * APPLY_DISCOUNT.getDetails())));
        System.out.println();
    }

    @Override
    public void printTotalAmountAfterDiscount(int totalAmountAfterDiscount) {
        this.printGuidance(PREVIEW_TOTAL_AMOUNT_AFTER_DISCOUNT.getGuidance());
        System.out.println(String.format(PREVIEW_MONEY.getGuidance(), formatCurrency(totalAmountAfterDiscount)));
        System.out.println();
    }

    @Override
    public void printBadge(BadgeCategory badge) {
        System.out.println(String.format(PREVIEW_EVENT_BADGE.getGuidance(), EVENT_MONTH.getDate()));
        System.out.println(badge.getCategory());
        System.out.println();
    }

    @Override
    public void printEventNotApply(String guidance) {
        this.printGuidance(guidance);
        System.out.println(PREVIEW_EVENT_NOT_APPLY.getGuidance());
        System.out.println();
    }

    private String formatCurrency(int amount) {
        NumberFormat currencyFormat = NumberFormat.getNumberInstance();
        currencyFormat.setGroupingUsed(true); // This is usually the default, but set explicitly for clarity
        return currencyFormat.format(amount);
    }
}
