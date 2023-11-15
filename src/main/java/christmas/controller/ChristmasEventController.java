package christmas.controller;

import static christmas.model.util.event.EventDetails.EVENT_STANDARD;

import christmas.model.domain.VisitInformation;
import christmas.model.dto.ClientDto;
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

    public ClientDto addClient() {
        VisitInformation visitInformation = userController.getVisitInformation();
        return userController.saveVisitInformation(visitInformation);
    }

    public void christmasEvent(ClientDto clientDto) {
        userController.sendWelcome();
        userController.guideVisitInformation(clientDto.visitInformation());
        int getTotalAmountBeforeDiscount = eventController.getTotalAmountBeforeDiscount(clientDto.visitInformation());
        userController.guideTotalAmountBeforeDiscount(getTotalAmountBeforeDiscount);

        EventHandlingStrategy eventHandlingStrategy = applyStrategy(getTotalAmountBeforeDiscount);
        eventHandlingStrategy.handleEvent(clientDto.visitInformation(), userController, eventController);
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
