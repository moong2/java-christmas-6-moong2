package christmas.strategy;

import christmas.controller.EventController;
import christmas.controller.UserController;
import christmas.model.domain.Client;

public class BenefitsAvailableStrategy implements EventHandlingStrategy {
    @Override
    public void handleEvent(Client client, UserController userController,
                            EventController eventController) {
        userController.guideGivenMenu(eventController.givenMenu(client));
        userController.guideBenefitsDetails(eventController.benefitsDetails(client));
        userController.guideBenefitsAmount(eventController.benefitsAmount(client));
        userController.guideTotalAmountAfterDiscount(
                eventController.getTotalAmountAfterDiscount(client));
        userController.guideBadge(eventController.getBadge(client));
    }
}
