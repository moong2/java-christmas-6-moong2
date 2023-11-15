package christmas.strategy;

import christmas.controller.EventController;
import christmas.controller.UserController;
import christmas.model.domain.VisitInformation;

public interface EventHandlingStrategy {
    void handleEvent(VisitInformation visitInformation, UserController userController,
                     EventController eventController);
}
