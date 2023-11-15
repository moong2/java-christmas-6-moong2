package christmas.controller;

import static christmas.model.util.event.EventDetails.EVENT_STANDARD;

import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.model.dto.VisitInformationDto;
import christmas.strategy.BenefitsAvailableStrategy;
import christmas.strategy.BenefitsUnavailableStrategy;
import christmas.strategy.EventHandlingStrategy;

public class ChristmasEventController {
    private final UserController userController = new UserController();
    private final EventController eventController = new EventController();
    private final ViewController viewController;

    public ChristmasEventController(ViewController viewController) {
        this.viewController = viewController;
    }

    public Client addClient() {
        VisitInformationDto visitInformationDto = viewController.getVisitInformation();
        return userController.addClient(visitInformationDto);
    }

    public Admin addAdmin() {
        return userController.addAdmin();
    }

    public void applyChristmasEvent(Client client) {
        viewController.sendWelcome();
        viewController.guideVisitInformation(client.getVisitInformation());
        int getTotalAmountBeforeDiscount = eventController.getTotalAmountBeforeDiscount(client);
        viewController.guideTotalAmountBeforeDiscount(getTotalAmountBeforeDiscount);

        EventHandlingStrategy eventHandlingStrategy = applyStrategy(getTotalAmountBeforeDiscount);
        eventHandlingStrategy.handleEvent(client, viewController, eventController);
    }

    public void analyzeEvent(Admin admin) {
        viewController.reportTotalNumberOfClients(eventController.analyzeTotalParticipants(admin));
        viewController.reportTotalNumberOfNextEventClients(eventController.analyzeNextEventParticipants(admin));
        viewController.reportTotalAmountOfOrders(eventController.analyzeTotalEventOrders(admin));
    }

    public void eventClose() {
        viewController.closeProcess();
    }

    private EventHandlingStrategy applyStrategy(int getTotalAmountBeforeDiscount) {
        if (getTotalAmountBeforeDiscount >= EVENT_STANDARD.getDetails()) {
            return new BenefitsAvailableStrategy();
        }
        return new BenefitsUnavailableStrategy();
    }
}
