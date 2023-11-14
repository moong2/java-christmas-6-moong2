package christmas.strategy;

import christmas.controller.EventController;
import christmas.controller.VisitInformationController;
import christmas.model.domain.VisitInformation;

public interface EventHandlingStrategy {
    void handleEvent(VisitInformation visitInformation, VisitInformationController visitInformationController,
                     EventController eventController);
}
