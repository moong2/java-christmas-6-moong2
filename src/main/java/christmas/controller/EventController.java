package christmas.controller;

import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import christmas.service.EventService;
import java.util.Map;

/**
 * EventController는 크리스마스 이벤트와 관련된 행동을 총괄한다.
 * 주문 금액에 따른 할인 정보, 증정 메뉴와 더불어 혜택을 수여하는 EventService와 통신한다.
 */
public class EventController {
    private final EventService eventService = new EventService();

    /**
     * 주어진 client의 주문에 의거하여 할인 전 총 주문 금액을 반환한다.
     *
     * @param client 총 주문 금액을 계산할 고객
     * @return integer 형식의 총 주문 금액
     */
    public int getTotalAmountBeforeDiscount(Client client) {
        return eventService.calculateTotalAmountBeforeDiscount(client);
    }

    /**
     * 주어진 client의 주문에 의거하여 고객이 받을 수 있는 증정 메뉴를 반환한다.
     *
     * @param client 증정 메뉴를 받으려는 고객
     * @return MenuList 형식의 증정 메뉴
     */
    public MenuList givenMenu(Client client) {
        return eventService.givenEvent(client);
    }

    /**
     * 주어진 client의 주문에 의거하여 고객이 받을 수 있는 혜택 내역을 반환한다.
     *
     * @param client 혜택을 받으려는 고객
     * @return 혜택 주제인 EventCategory, 해당 category에서의 할인 내역을 Integer 형식으로 담는 Map
     */
    public Map<EventCategory, Integer> benefitsDetails(Client client) {
        return eventService.events(client);
    }

    /**
     * 주어진 client의 주문에 의거하여 고객이 받을 수 있는 혜택가를 반환한다.
     *
     * @param client 혜택가를 계산하려는 고객
     * @return integer 형식의 혜택가
     */
    public int benefitsAmount(Client client) {
        return eventService.calculateBenefitsAmount(client);
    }

    /**
     * 주어진 client의 주문에 의거하여 고객이 할인 후 지불해야하는 금액을 반환한다.
     *
     * @param client 할인 후 금액을 계산하려는 고객
     * @return integer 형식의 할인 후 금액
     */
    public int getTotalAmountAfterDiscount(Client client) {
        return eventService.calculateTotalAmountAfterDiscount(client);
    }

    /**
     * 주어진 client의 주문에 의거하여 고객에게 배지를 수여한다.
     *
     * @param client 배지를 받으려는 고객
     * @return BadgeCategory 형식의 배지
     */
    public BadgeCategory getBadge(Client client) {
        return eventService.awardBadge(client);
    }

    /**
     * admin이 이벤트의 총 참여자 수를 계산할 수 있도록 한다.
     *
     * @param admin 이벤트 총 참여자 수를 계산하려는 admin
     * @return integer 형식의 이벤트 총 참여자 수
     */
    public int analyzeTotalParticipants(Admin admin) {
        return eventService.analyzeTotalParticipants(admin);
    }

    /**
     * admin이 이벤트의 총 참여자 수를 기반으로 다음 이벤트인 설날 이벤트의 참여자 수의 기댓값을 계산할 수 있도록 한다.
     *
     * @param admin 다음 이벤트 참여자 수를 계산하려는 admin
     * @return double 형식의 다음 이벤트 예상 참여자 수
     */
    public double analyzeNextEventParticipants(Admin admin) {
        return eventService.analyzeNextEventParticipants(admin);
    }

    /**
     * admin이 이벤트 총 참여자의 주문내역을 기반으로 총 판매 금액을 계산할 수 있도록 한다.
     *
     * @param admin 총 판매 금액을 계산하려는 admin
     * @return 총 판매 금액
     */
    public Long analyzeTotalEventOrders(Admin admin) {
        return eventService.analyzeTotalEventOrders(admin);
    }
}
