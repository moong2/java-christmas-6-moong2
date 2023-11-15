package christmas.model.domain;

import christmas.model.util.menu.MenuList;
import christmas.model.util.user.UserRoles;

/**
 * 크리스마스 이벤트에 참여할 고객을 나타낸다. Client의 방문 정보와 권한을 캡슐화하며, 접근할 메소드를 제공한다. Client의 주문을 토대로 받을 수 있는 혜택에 대한 계산 메소드를 제공한다.
 */
public class Client {
    private final VisitInformation visitInformation;
    private final Role role;

    private Client(VisitInformation visitInformation, Role role) {
        this.visitInformation = visitInformation;
        this.role = role;
    }

    /**
     * Client를 생성하는 팩토리메소드이다.
     *
     * @param visitInformation
     * @return
     */
    public static Client of(VisitInformation visitInformation) {
        return new Client(visitInformation, new Role(UserRoles.CLIENT));
    }

    /**
     * Client의 방문 정보를 반환한다.
     *
     * @return Client의 방문 정보
     */
    public VisitInformation getVisitInformation() {
        return this.visitInformation;
    }

    /**
     * Client의 방문 정보를 기반으로 할인 전 총 주문 금액을 전달받는다.
     *
     * @return integer 형식의 할인 전 총 주문 금액
     */
    public int getTotalAmountBeforeDiscount() {
        return this.visitInformation.getTotalAmountBeforeDiscount();
    }

    /**
     * Client의 방문 정보를 기반으로 증정 메뉴를 전달 받는다.
     *
     * @return MenuList 형식의 증정 메뉴
     */
    public MenuList getGivenMenu() {
        return this.visitInformation.getGivenMenu();
    }

    /**
     * Client의 방문 정보를 기반으로 크리스마스 디데이 이벤트 할인가를 전달 받는다.
     *
     * @return integer 형식의 크리스마스 디데이 이벤트 할인가
     */
    public int getChristmasDDayDiscount() {
        return this.visitInformation.getChristmasDDayDiscount();
    }

    /**
     * Client의 방문 정보를 기반으로 평일 이벤트 할인가를 전달 받는다.
     *
     * @return integer 형식의 평일 이벤트 할인가
     */
    public int getWeekdayDiscount() {
        return this.visitInformation.getWeekdayDiscount();
    }

    /**
     * Client의 방문 정보를 기반으로 주말 이벤트 할인가를 전달 받는다.
     *
     * @return integer 형식의 주말 이벤트 할인가
     */
    public int getWeekendDiscount() {
        return this.visitInformation.getWeekendDiscount();
    }

    /**
     * Client의 방문 정보를 기반으로 특별 이벤트 할인가를 전달 받는다.
     *
     * @return integer 형식의 특별 이벤트 할인가
     */
    public int getSpecialDiscount() {
        return this.visitInformation.getSpecialDiscount();
    }
}
