package christmas.strategy;

import christmas.controller.EventController;
import christmas.controller.ViewController;
import christmas.model.domain.Client;

/**
 * EventHandlingStrategy는 크리스마스 이벤트에 적용할 이벤트 전략을 정의하는 인터페이스이다. 이 인터페이스의 구현부는 client에게 어떤 이벤트를 적용시킬지 결정한다.
 */
public interface EventHandlingStrategy {
    /**
     * 주어진 client의 이벤트를 처리한다. 증정 메뉴, 할인을 포함한 이벤트 전략을 정의한다.
     *
     * @param client          이벤트를 처리 당할 고객
     * @param viewController  적용된 이벤트를 출력할 컨트롤러
     * @param eventController 이벤트와 관련된 메소드를 사용할 컨트롤러
     */
    void handleEvent(Client client, ViewController viewController,
                     EventController eventController);
}
