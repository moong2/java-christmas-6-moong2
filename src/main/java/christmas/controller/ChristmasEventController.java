package christmas.controller;

import static christmas.model.util.event.EventDetails.EVENT_STANDARD;

import christmas.model.domain.VisitInformation;
import christmas.strategy.BenefitsAvailableStrategy;
import christmas.strategy.BenefitsUnavailableStrategy;
import christmas.strategy.EventHandlingStrategy;

public class ChristmasEventController {
    private final VisitInformationController visitInformationController;
    private final EventController eventController;

    public ChristmasEventController(VisitInformationController visitInformationController) {
        this.visitInformationController = visitInformationController;
        this.eventController = new EventController();
    }

    public void welcome() {
        visitInformationController.sendWelcome();
    }

    public void christmasEvent() {
        VisitInformation visitInformation = visitInformationController.getVisitInformation();
        visitInformationController.saveVisitInformation(visitInformation);

        visitInformationController.guideVisitInformation(visitInformation);
        int getTotalAmountBeforeDiscount = eventController.getTotalAmountBeforeDiscount(visitInformation);
        visitInformationController.guideTotalAmountBeforeDiscount(getTotalAmountBeforeDiscount);

        EventHandlingStrategy eventHandlingStrategy = applyStrategy(getTotalAmountBeforeDiscount);
        eventHandlingStrategy.handleEvent(visitInformation, visitInformationController, eventController);
    }

    public void eventClose() {
        visitInformationController.closeProcess();
    }

    private EventHandlingStrategy applyStrategy(int getTotalAmountBeforeDiscount) {
        if (getTotalAmountBeforeDiscount >= EVENT_STANDARD.getDetails()) {
            return new BenefitsAvailableStrategy();
        }
        return new BenefitsUnavailableStrategy();
    }
}
