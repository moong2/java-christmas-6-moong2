package christmas.service;

import static christmas.model.util.event.EventCategory.CHRISTMAS_DDAY_EVENT;
import static christmas.model.util.event.EventCategory.GIVE_EVENT;
import static christmas.model.util.event.EventCategory.SPECIAL_EVENT;
import static christmas.model.util.event.EventCategory.WEEKDAY_EVENT;
import static christmas.model.util.event.EventCategory.WEEKEND_EVENT;
import static christmas.model.util.event.EventDetails.NONE_DISCOUNT;
import static christmas.model.util.menu.MenuList.CHAMPAGNE;

import christmas.model.domain.Users;
import christmas.model.domain.VisitInformation;
import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import java.util.HashMap;
import java.util.Map;

public class EventService {
    private final Users users = Users.getInstance();

    public int calculateTotalAmountBeforeDiscount(VisitInformation visitInformation) {
        return visitInformation.getTotalAmountBeforeDiscount();
    }

    public MenuList givenEvent(VisitInformation visitInformation) {
        return visitInformation.getGivenMenu();
    }

    public Map<EventCategory, Integer> events(VisitInformation visitInformation) {
        Map<EventCategory, Integer> totalBenefits = new HashMap<>();

        addDiscountIfApplicable(totalBenefits, CHRISTMAS_DDAY_EVENT, visitInformation.getChristmasDDayDiscount());
        addDiscountIfApplicable(totalBenefits, WEEKDAY_EVENT, visitInformation.getWeekdayDiscount());
        addDiscountIfApplicable(totalBenefits, WEEKEND_EVENT, visitInformation.getWeekendDiscount());
        addDiscountIfApplicable(totalBenefits, SPECIAL_EVENT, visitInformation.getSpecialDiscount());
        addDiscountIfApplicable(totalBenefits, GIVE_EVENT, visitInformation.getGivenMenu().getPrice());

        return totalBenefits;
    }

    public int calculateBenefitsAmount(VisitInformation visitInformation) {
        return this.events(visitInformation)
                .entrySet()
                .stream()
                .mapToInt((benefit) -> benefit.getValue())
                .sum();
    }

    public int calculateTotalAmountAfterDiscount(VisitInformation visitInformation) {
        int totalAmountBeforeDiscount = this.calculateTotalAmountBeforeDiscount(visitInformation);

        int discountAmount = this.calculateBenefitsAmount(visitInformation);
        MenuList givenMenu = this.givenEvent(visitInformation);
        if (givenMenu.equals(CHAMPAGNE)) {
            discountAmount += CHAMPAGNE.getPrice();
        }

        return totalAmountBeforeDiscount - discountAmount;
    }

    public BadgeCategory awardBadge(VisitInformation visitInformation) {
        return BadgeCategory.getBadge(this.calculateBenefitsAmount(visitInformation));
    }

    private void addDiscountIfApplicable(Map<EventCategory, Integer> benefits, EventCategory category, int discount) {
        if (discount != NONE_DISCOUNT.getDetails()) {
            benefits.put(category, discount);
        }
    }
}
