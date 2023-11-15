package christmas.view;

import static christmas.util.instructions.Guidance.ORDER_MENU;
import static christmas.util.instructions.Guidance.VISIT_DATE;

import camp.nextstep.edu.missionutils.Console;

/**
 * ChristmasInputView는 InputView 인터페이스의 구현부다. 12월 크리스마스 이벤트의 입력값을 받는 책임이 있다.
 */
public class ChristmasInputView implements InputView {
    /**
     * 방문 날짜를 입력하라는 문구 안내 후, 방문 날짜를 입력 받는다.
     *
     * @return 입력 받은 String 형식의 방문 날짜
     */
    @Override
    public String getVisitDate() {
        System.out.println(VISIT_DATE.getGuidance());
        return Console.readLine();
    }

    /**
     * 주문 메뉴를 입력하라는 문구 안내 후, 주문 내역을 입력 받는다.
     *
     * @return 입력 받은 String 형식의 주문 내역
     */
    @Override
    public String getOrders() {
        System.out.println(ORDER_MENU.getGuidance());
        return Console.readLine();
    }
}
