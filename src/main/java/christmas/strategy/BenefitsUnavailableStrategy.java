package christmas.strategy;

import static christmas.model.util.event.EventPeriod.EVENT_MONTH;
import static christmas.util.instructions.Guidance.PREVIEW_EVENT_BADGE;
import static christmas.util.instructions.Guidance.PREVIEW_GIVE_MENU;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS_AMOUNT;

import christmas.controller.EventController;
import christmas.controller.VisitInformationController;
import christmas.model.domain.VisitInformation;

public class BenefitsUnavailableStrategy implements EventHandlingStrategy {
    @Override
    public void handleEvent(VisitInformation visitInformation, VisitInformationController visitInformationController,
                            EventController eventController) {
        visitInformationController.guideEventNotApply(PREVIEW_GIVE_MENU.getGuidance());
        visitInformationController.guideEventNotApply(PREVIEW_TOTAL_BENEFITS.getGuidance());
        visitInformationController.guideEventNotApply(PREVIEW_TOTAL_BENEFITS_AMOUNT.getGuidance());
        visitInformationController.guideTotalAmountAfterDiscount(
                eventController.getTotalAmountAfterDiscount(visitInformation));
        visitInformationController.guideEventNotApply(String.format(PREVIEW_EVENT_BADGE.getGuidance(), EVENT_MONTH.getDate()));
    }
}
