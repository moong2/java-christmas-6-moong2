package christmas.view;

/**
 * InputView는 프로그램의 입력을 담당하는 View 인터페이스다.
 */
public interface InputView {
    /**
     * 방문 날짜를 입력 받는다.
     *
     * @return String 형식의 방문 날짜
     */
    String getVisitDate();

    /**
     * 주문 내역을 입력 받는다.
     *
     * @return String 형식의 주문 내역
     */
    String getOrders();
}
