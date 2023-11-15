package christmas.view;

import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import java.util.Map;

/**
 * OutputView는 프로그램의 출력을 담당하는 View다.
 */
public interface OutputView {
    /**
     * 주의 사항을 출력한다.
     */
    void printPrecaution();

    /**
     * 안내 문구를 출력한다.
     *
     * @param message 안내 문구
     */
    void printGuidance(String message);

    /**
     * 입력 받은 방문 정보를 기반으로 이벤트 혜택 미리 보기를 출력한다.
     *
     * @param visitDate 방문 날짜
     */
    void printPreview(int visitDate);

    /**
     * 주문 내역을 출력한다.
     *
     * @param orders 주문 내역
     */
    void printOrderMenus(Map<String, Integer> orders);

    /**
     * 할인 전 총주문 금액을 출력한다.
     *
     * @param totalAmountBeforeDiscount 할인 전 총주문 금액
     */
    void printTotalAmountBeforeDiscount(int totalAmountBeforeDiscount);

    /**
     * 증정 메뉴를 출력한다.
     *
     * @param menuList 증정 메뉴
     */
    void printGivenMenu(MenuList menuList);

    /**
     * 혜택 내역을 출력한다.
     *
     * @param benefits 혜택 내역
     */
    void printBenefits(Map<EventCategory, Integer> benefits);

    /**
     * 총혜택금액을 출력한다.
     *
     * @param totalBenefitsAmount 총혜택금액
     */
    void printTotalBenefitsAmount(int totalBenefitsAmount);

    /**
     * 할인 후 예상 결제 금액을 출력한다.
     *
     * @param totalAmountAfterDiscount 할인 후 예상 결제 금액
     */
    void printTotalAmountAfterDiscount(int totalAmountAfterDiscount);

    /**
     * 수여받은 배지를 출력한다.
     *
     * @param badge 배지
     */
    void printBadge(BadgeCategory badge);

    /**
     * 적용되지 않은 이벤트를 출력한다.
     *
     * @param guidance 이벤트 문구
     */
    void printEventNotApply(String guidance);

    /**
     * admin이 분석한 통계 자료의 헤더를 출력한다.
     *
     * @param message 통계 자료의 헤더
     */
    void printHeader(String message);

    /**
     * 이벤트에 참여한 총 고객 수를 출력한다.
     *
     * @param totalNumberOfClients 이벤트에 참여한 총 고객 수
     */
    void printNumberOfClients(int totalNumberOfClients);

    /**
     * 다음 이벤트에 재참여할 것으로 예상되는 고객 수를 출력한다.
     *
     * @param totalNumberOfNextClients 다음 설날 이벤트 시, 예상 재참여 고객 수
     */
    void printNumberOfNextClients(double totalNumberOfNextClients);

    /**
     * 총 판매 금액을 출력한다.
     *
     * @param totalAmountOfOrders 총 판매 금액
     */
    void printTotalAmountOfOrders(long totalAmountOfOrders);

    /**
     * 예외를 출력한다.
     *
     * @param message 예외 메시지
     */
    void printException(String message);

}
