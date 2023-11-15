package christmas.strategy;

import christmas.controller.EventController;
import christmas.controller.UserController;
import christmas.model.domain.Client;
import christmas.model.domain.VisitInformation;

public interface EventHandlingStrategy {
    void handleEvent(Client client, UserController userController,
                     EventController eventController);
}
