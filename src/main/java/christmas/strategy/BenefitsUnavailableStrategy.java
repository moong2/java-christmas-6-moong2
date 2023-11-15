package christmas.strategy;

import static christmas.model.util.event.EventPeriod.EVENT_MONTH;
import static christmas.util.instructions.Guidance.PREVIEW_EVENT_BADGE;
import static christmas.util.instructions.Guidance.PREVIEW_GIVE_MENU;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS_AMOUNT;

import christmas.controller.EventController;
import christmas.controller.UserController;
import christmas.model.domain.Client;
import christmas.model.domain.VisitInformation;

public class BenefitsUnavailableStrategy implements EventHandlingStrategy {
    @Override
    public void handleEvent(Client client, UserController userController,
                            EventController eventController) {
        userController.guideEventNotApply(PREVIEW_GIVE_MENU.getGuidance());
        userController.guideEventNotApply(PREVIEW_TOTAL_BENEFITS.getGuidance());
        userController.guideEventNotApply(PREVIEW_TOTAL_BENEFITS_AMOUNT.getGuidance());
        userController.guideTotalAmountAfterDiscount(
                eventController.getTotalAmountAfterDiscount(client));
        userController.guideEventNotApply(String.format(PREVIEW_EVENT_BADGE.getGuidance(), EVENT_MONTH.getDate()));
    }
}
