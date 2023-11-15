package christmas.strategy;

import christmas.controller.EventController;
import christmas.controller.ViewController;
import christmas.model.domain.Client;

public interface EventHandlingStrategy {
    void handleEvent(Client client, ViewController viewController,
                     EventController eventController);
}
