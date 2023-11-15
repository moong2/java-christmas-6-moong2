package christmas.service;

import static christmas.model.util.event.EventCategory.CHRISTMAS_DDAY_EVENT;
import static christmas.model.util.event.EventCategory.GIVE_EVENT;
import static christmas.model.util.event.EventCategory.SPECIAL_EVENT;
import static christmas.model.util.event.EventCategory.WEEKDAY_EVENT;
import static christmas.model.util.event.EventCategory.WEEKEND_EVENT;
import static christmas.model.util.event.EventDetails.NONE_DISCOUNT;
import static christmas.model.util.menu.MenuList.CHAMPAGNE;

import christmas.model.domain.Client;
import christmas.model.domain.Users;
import christmas.model.domain.VisitInformation;
import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import java.util.HashMap;
import java.util.Map;

public class EventService {
    private final Users users = Users.getInstance();

    public int calculateTotalAmountBeforeDiscount(Client client) {
        return client.getTotalAmountBeforeDiscount();
    }

    public MenuList givenEvent(Client client) {
        return client.getGivenMenu();
    }

    public Map<EventCategory, Integer> events(Client client) {
        Map<EventCategory, Integer> totalBenefits = new HashMap<>();

        addDiscountIfApplicable(totalBenefits, CHRISTMAS_DDAY_EVENT, client.getChristmasDDayDiscount());
        addDiscountIfApplicable(totalBenefits, WEEKDAY_EVENT, client.getWeekdayDiscount());
        addDiscountIfApplicable(totalBenefits, WEEKEND_EVENT, client.getWeekendDiscount());
        addDiscountIfApplicable(totalBenefits, SPECIAL_EVENT, client.getSpecialDiscount());
        addDiscountIfApplicable(totalBenefits, GIVE_EVENT, client.getGivenMenu().getPrice());

        return totalBenefits;
    }

    public int calculateBenefitsAmount(Client client) {
        return this.events(client)
                .entrySet()
                .stream()
                .mapToInt((benefit) -> benefit.getValue())
                .sum();
    }

    public int calculateTotalAmountAfterDiscount(Client client) {
        int totalAmountBeforeDiscount = this.calculateTotalAmountBeforeDiscount(client);

        int discountAmount = this.calculateBenefitsAmount(client);
        MenuList givenMenu = this.givenEvent(client);
        if (givenMenu.equals(CHAMPAGNE)) {
            discountAmount += CHAMPAGNE.getPrice();
        }

        return totalAmountBeforeDiscount - discountAmount;
    }

    public BadgeCategory awardBadge(Client client) {
        return BadgeCategory.getBadge(this.calculateBenefitsAmount(client));
    }

    private void addDiscountIfApplicable(Map<EventCategory, Integer> benefits, EventCategory category, int discount) {
        if (discount != NONE_DISCOUNT.getDetails()) {
            benefits.put(category, discount);
        }
    }
}
