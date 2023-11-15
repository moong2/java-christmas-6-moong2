package christmas.strategy;

import christmas.controller.EventController;
import christmas.controller.ViewController;
import christmas.model.domain.Client;

/**
 * BenefitsAvailableStrategy는 EventHandlingStrategy 인터페이스의 구현부이다. 이벤트를 적용할 수 있는 Client의 이벤트를 처리한다. 증정 메뉴, 할인가를 포함한 이벤트를
 * 처리한다.
 */
public class BenefitsAvailableStrategy implements EventHandlingStrategy {
    /**
     * 참여할 수 있는 이벤트를 처리한다. 증정 메뉴, 혜택 내역, 총혜택금액, 총할인가, 배지 수여를 처리한다.
     *
     * @param client          이벤트를 처리 당할 고객
     * @param viewController  적용된 이벤트를 출력할 컨트롤러
     * @param eventController 이벤트와 관련된 메소드를 사용할 컨트롤러
     */
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
