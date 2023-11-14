package christmas.controller;

import christmas.model.domain.VisitInformation;
import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import christmas.service.EventService;
import java.util.Map;

public class EventController {
    private final EventService eventService = new EventService();

    public int getTotalAmountBeforeDiscount(VisitInformation visitInformation) {
        return eventService.calculateTotalAmountBeforeDiscount(visitInformation);
    }

    public MenuList givenMenu(VisitInformation visitInformation) {
        return eventService.givenEvent(visitInformation);
    }

    public Map<EventCategory, Integer> benefitsDetails(VisitInformation visitInformation) {
        return eventService.events(visitInformation);
    }

    public int benefitsAmount(VisitInformation visitInformation) {
        return eventService.calculateBenefitsAmount(visitInformation);
    }

    public int getTotalAmountAfterDiscount(VisitInformation visitInformation) {
        return eventService.calculateTotalAmountAfterDiscount(visitInformation);
    }

    public BadgeCategory getBadge(VisitInformation visitInformation) {
        return eventService.awardBadge(visitInformation);
    }
}
