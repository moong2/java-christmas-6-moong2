package christmas.strategy;

import static christmas.model.util.event.EventPeriod.EVENT_MONTH;
import static christmas.util.instructions.Guidance.PREVIEW_EVENT_BADGE;
import static christmas.util.instructions.Guidance.PREVIEW_GIVE_MENU;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS_AMOUNT;

import christmas.controller.EventController;
import christmas.controller.ViewController;
import christmas.model.domain.Client;

public class BenefitsUnavailableStrategy implements EventHandlingStrategy {
    @Override
    public void handleEvent(Client client, ViewController viewController,
                            EventController eventController) {
        viewController.guideEventNotApply(PREVIEW_GIVE_MENU.getGuidance());
        viewController.guideEventNotApply(PREVIEW_TOTAL_BENEFITS.getGuidance());
        viewController.guideEventNotApply(PREVIEW_TOTAL_BENEFITS_AMOUNT.getGuidance());
        viewController.guideTotalAmountAfterDiscount(
                eventController.getTotalAmountAfterDiscount(client));
        viewController.guideEventNotApply(String.format(PREVIEW_EVENT_BADGE.getGuidance(), EVENT_MONTH.getDate()));
    }
}
