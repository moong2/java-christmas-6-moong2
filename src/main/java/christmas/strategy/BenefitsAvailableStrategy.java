package christmas.strategy;

import christmas.controller.EventController;
import christmas.controller.UserController;
import christmas.controller.ViewController;
import christmas.model.domain.Client;

public class BenefitsAvailableStrategy implements EventHandlingStrategy {
    @Override
    public void handleEvent(Client client, ViewController viewController,
                            EventController eventController) {
        viewController.guideGivenMenu(eventController.givenMenu(client));
        viewController.guideBenefitsDetails(eventController.benefitsDetails(client));
        viewController.guideBenefitsAmount(eventController.benefitsAmount(client));
        viewController.guideTotalAmountAfterDiscount(
                eventController.getTotalAmountAfterDiscount(client));
        viewController.guideBadge(eventController.getBadge(client));
    }
}
