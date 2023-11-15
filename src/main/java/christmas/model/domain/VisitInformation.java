package christmas.model.domain;

import static christmas.model.calculator.EventCalculator.christmasDDayEvent;
import static christmas.model.calculator.EventCalculator.givenMenu;
import static christmas.model.calculator.EventCalculator.specialEvent;
import static christmas.model.calculator.EventCalculator.weekdayEvent;
import static christmas.model.calculator.EventCalculator.weekendEvent;

import christmas.model.util.menu.MenuList;
import java.time.LocalDate;

/**
 * Client의 방문 정보를 담고 있다.
 * 방문할 날짜와 주문 메뉴를 캡슐화한다.
 */
public class VisitInformation {
    private final LocalDate visitDate;
    private final Orders orders;

    private VisitInformation(LocalDate visitDate, Orders orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }

    /**
     * VisitInformation을 생성하는 팩토리 메소드이다.
     *
     * @param localDate 방문할 날짜
     * @param orders 주문 메뉴
     * @return 새 VisitInformation 객체
     */
    public static VisitInformation of(LocalDate localDate, Orders orders) {
        return new VisitInformation(localDate, orders);
    }

    /**
     * 할인 전 총 주문 금액을 반환한다.
     *
     * @return integer 형식의 할인 전 총 주문 금액
     */
    public int getTotalAmountBeforeDiscount() {
        return orders.calculateTotalAmountBeforeDiscount();
    }

    /**
     * 할인 전 총 주문 금액을 기준으로 증정 메뉴를 반환한다.
     *
     * @return MenuList 형식의 증정 메뉴
     */
    public MenuList getGivenMenu() {
        int totalAmountBeforeDiscount = this.getTotalAmountBeforeDiscount();

        return givenMenu(totalAmountBeforeDiscount);
    }

    /**
     * 방문 날짜를 기준으로 크리스마스 디데이 이벤트 할인가를 반환한다.
     *
     * @return integer 형식의 크리스마스 디데이 이벤트 할인가
     */
    public int getChristmasDDayDiscount() {
        return christmasDDayEvent(visitDate);
    }

    /**
     * 주문 메뉴와 방문 날짜를 기준으로 평일 이벤트 할인가를 반환한다.
     *
     * @return integer 형식의 평일 이벤트 할인가
     */
    public int getWeekdayDiscount() {
        return weekdayEvent(orders.getOrders(), visitDate);
    }

    /**
     * 주문 메뉴와 방문 날짜를 기준으로 주말 이벤트 할인가를 반환한다.
     *
     * @return integer 형식의 주말 이벤트 할인가
     */
    public int getWeekendDiscount() {
        return weekendEvent(orders.getOrders(), visitDate);
    }

    /**
     * 방문 날짜를 기준으로 특별 이벤트 할인가를 반환한다.
     *
     * @return integer 형식의 특별 이벤트 할인가
     */
    public int getSpecialDiscount() {
        return specialEvent(visitDate);
    }

    /**
     * 방문 날짜를 반환한다.
     *
     * @return LocalDate 형식의 방문 날짜
     */
    public LocalDate getVisitDate() {
        return visitDate;
    }

    /**
     * 주문 내역을 반환한다.
     *
     * @return 메뉴 이름의 String과 주문 개수의 Integer의 wrapper 클래스인 Orders 형식의 주문 내역
     */
    public Orders getOrders() {
        return orders;
    }
}
