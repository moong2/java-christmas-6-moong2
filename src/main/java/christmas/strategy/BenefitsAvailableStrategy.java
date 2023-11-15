package christmas.strategy;

import christmas.controller.EventController;
import christmas.controller.UserController;
import christmas.model.domain.VisitInformation;

public class BenefitsAvailableStrategy implements EventHandlingStrategy {
    @Override
    public void handleEvent(VisitInformation visitInformation, UserController userController,
                            EventController eventController) {
        userController.guideGivenMenu(eventController.givenMenu(visitInformation));
        userController.guideBenefitsDetails(eventController.benefitsDetails(visitInformation));
        userController.guideBenefitsAmount(eventController.benefitsAmount(visitInformation));
        userController.guideTotalAmountAfterDiscount(
                eventController.getTotalAmountAfterDiscount(visitInformation));
        userController.guideBadge(eventController.getBadge(visitInformation));
    }
}
