package christmas.strategy;

import static christmas.model.util.event.EventPeriod.EVENT_MONTH;
import static christmas.util.instructions.Guidance.PREVIEW_EVENT_BADGE;
import static christmas.util.instructions.Guidance.PREVIEW_GIVE_MENU;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS;
import static christmas.util.instructions.Guidance.PREVIEW_TOTAL_BENEFITS_AMOUNT;

import christmas.controller.EventController;
import christmas.controller.ViewController;
import christmas.model.domain.Client;

/**
 * BenefitsUnavailableStrategy는 EventHandlingStrategy 인터페이스의 구현부이다. 이벤트를 적용할 수 없는 Client의 이벤트를 처리한다. 증정 메뉴, 할인가를 포함한
 * 이벤트를 처리하지 않는다.
 */
public class BenefitsUnavailableStrategy implements EventHandlingStrategy {
    /**
     * 참여할 수 없는 이벤트를 처리한다. 증정 메뉴, 헤택 내역, 총헤택금액, 배지 부여를 처리하지 않는다.
     *
     * @param client          이벤트를 처리 당할 고객
     * @param viewController  적용된 이벤트를 출력할 컨트롤러
     * @param eventController 이벤트와 관련된 메소드를 사용할 컨트롤러
     */
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
