package christmas.controller;

import static christmas.model.util.event.EventDetails.EVENT_STANDARD;

import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.model.domain.VisitInformation;
import christmas.strategy.BenefitsAvailableStrategy;
import christmas.strategy.BenefitsUnavailableStrategy;
import christmas.strategy.EventHandlingStrategy;

public class ChristmasEventController {
    private final UserController userController;
    private final EventController eventController;

    public ChristmasEventController(UserController userController) {
        this.userController = userController;
        this.eventController = new EventController();
    }

    public Client addClient() {
        VisitInformation visitInformation = userController.getVisitInformation();
        return userController.saveVisitInformation(visitInformation);
    }

    public Admin addAdmin() {
        return userController.addAdmin();
    }

    public void applyChristmasEvent(Client client) {
        userController.sendWelcome();
        userController.guideVisitInformation(client.getVisitInformation());
        int getTotalAmountBeforeDiscount = eventController.getTotalAmountBeforeDiscount(client);
        userController.guideTotalAmountBeforeDiscount(getTotalAmountBeforeDiscount);

        EventHandlingStrategy eventHandlingStrategy = applyStrategy(getTotalAmountBeforeDiscount);
        eventHandlingStrategy.handleEvent(client, userController, eventController);
    }

    public void analyzeEvent(Admin admin) {
        int i = eventController.analyzeTotalParticipants(admin);
        double v = eventController.analyzeNextEventParticipants(admin);
        Long aLong = eventController.analyzeTotalEventOrders(admin);

        System.out.println("i = " + i);
        System.out.println("v = " + v);
        System.out.println("aLong = " + aLong);
    }

    public void eventClose() {
        userController.closeProcess();
    }

    private EventHandlingStrategy applyStrategy(int getTotalAmountBeforeDiscount) {
        if (getTotalAmountBeforeDiscount >= EVENT_STANDARD.getDetails()) {
            return new BenefitsAvailableStrategy();
        }
        return new BenefitsUnavailableStrategy();
    }
}
