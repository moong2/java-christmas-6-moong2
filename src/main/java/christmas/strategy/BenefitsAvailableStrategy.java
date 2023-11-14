package christmas.strategy;

import christmas.controller.EventController;
import christmas.controller.VisitInformationController;
import christmas.model.domain.VisitInformation;

public class BenefitsAvailableStrategy implements EventHandlingStrategy {
    @Override
    public void handleEvent(VisitInformation visitInformation, VisitInformationController visitInformationController,
                            EventController eventController) {
        visitInformationController.guideGivenMenu(eventController.givenMenu(visitInformation));
        visitInformationController.guideBenefitsDetails(eventController.benefitsDetails(visitInformation));
        visitInformationController.guideBenefitsAmount(eventController.benefitsAmount(visitInformation));
        visitInformationController.guideTotalAmountAfterDiscount(
                eventController.getTotalAmountAfterDiscount(visitInformation));
        visitInformationController.guideBadge(eventController.getBadge(visitInformation));
    }
}
