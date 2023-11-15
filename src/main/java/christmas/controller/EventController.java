package christmas.controller;

import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import christmas.service.EventService;
import java.util.Map;

public class EventController {
    private final EventService eventService = new EventService();

    public int getTotalAmountBeforeDiscount(Client client) {
        return eventService.calculateTotalAmountBeforeDiscount(client);
    }

    public MenuList givenMenu(Client client) {
        return eventService.givenEvent(client);
    }

    public Map<EventCategory, Integer> benefitsDetails(Client client) {
        return eventService.events(client);
    }

    public int benefitsAmount(Client client) {
        return eventService.calculateBenefitsAmount(client);
    }

    public int getTotalAmountAfterDiscount(Client client) {
        return eventService.calculateTotalAmountAfterDiscount(client);
    }

    public BadgeCategory getBadge(Client client) {
        return eventService.awardBadge(client);
    }

    public int analyzeTotalParticipants(Admin admin) {
        return eventService.analyzeTotalParticipants(admin);
    }

    public double analyzeNextEventParticipants(Admin admin) {
        return eventService.analyzeNextEventParticipants(admin);
    }

    public Long analyzeTotalEventOrders(Admin admin) {
        return eventService.analyzeTotalEventOrders(admin);
    }
}
