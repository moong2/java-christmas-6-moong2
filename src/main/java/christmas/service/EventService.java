package christmas.service;

import static christmas.model.util.event.EventCategory.CHRISTMAS_DDAY_EVENT;
import static christmas.model.util.event.EventCategory.GIVE_EVENT;
import static christmas.model.util.event.EventCategory.SPECIAL_EVENT;
import static christmas.model.util.event.EventCategory.WEEKDAY_EVENT;
import static christmas.model.util.event.EventCategory.WEEKEND_EVENT;
import static christmas.model.util.event.EventDetails.NONE_DISCOUNT;
import static christmas.model.util.menu.MenuList.CHAMPAGNE;

import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.model.domain.Users;
import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import java.util.HashMap;
import java.util.Map;

/**
 * EventService는 크리스마스 이벤트에 관련된 이벤트를 제공한다. 할인, 혜택, 주문 금액들과 더불어 배지 수여를 처리한다.
 */
public class EventService {
    private final Users users = Users.getInstance();

    /**
     * 주어진 client의 할인 전 주문 금액을 계산한다.
     *
     * @param client 할인 전 주문 금액을 계산할 client
     * @return integer 형식의 할인 전 주문 금액
     */
    public int calculateTotalAmountBeforeDiscount(Client client) {
        return client.getTotalAmountBeforeDiscount();
    }

    /**
     * 주어진 client의 증정 메뉴를 결정한다.
     *
     * @param client 증정 메뉴를 결정할 client
     * @return MenuList 형식의 증정 메뉴
     */
    public MenuList givenEvent(Client client) {
        return client.getGivenMenu();
    }

    /**
     * 주어진 client에게 적용될 이벤트 카테고리 내의 이벤트를 계산한다.
     *
     * @param client 이벤트를 적용할 client
     * @return 이벤트와 해당 이벤트의 할인가를 담고 있는 Map
     */
    public Map<EventCategory, Integer> events(Client client) {
        Map<EventCategory, Integer> totalBenefits = new HashMap<>();

        addDiscountIfApplicable(totalBenefits, CHRISTMAS_DDAY_EVENT, client.getChristmasDDayDiscount());
        addDiscountIfApplicable(totalBenefits, WEEKDAY_EVENT, client.getWeekdayDiscount());
        addDiscountIfApplicable(totalBenefits, WEEKEND_EVENT, client.getWeekendDiscount());
        addDiscountIfApplicable(totalBenefits, SPECIAL_EVENT, client.getSpecialDiscount());
        addDiscountIfApplicable(totalBenefits, GIVE_EVENT, client.getGivenMenu().getPrice());

        return totalBenefits;
    }

    /**
     * 주어진 client의 총혜택 금액을 계산한다.
     *
     * @param client 총혜택 금액을 계산할 client
     * @return integer 형식의 총혜택 금액
     */
    public int calculateBenefitsAmount(Client client) {
        return this.events(client)
                .entrySet()
                .stream()
                .mapToInt((benefit) -> benefit.getValue())
                .sum();
    }

    /**
     * 주어진 client의 할인 후 예상 결제 금액을 계산한다. 할인 전 총 주문금액에서 할인가를 제외한 금액을 반환한다. 할인가는 총혜택금액에서 증정 메뉴의 가격을 제외한 값이다.
     *
     * @param client 할인 후 예상 결제 금액을 계산할 client
     * @return 할인 후 예상 결제 금액
     */
    public int calculateTotalAmountAfterDiscount(Client client) {
        int totalAmountBeforeDiscount = this.calculateTotalAmountBeforeDiscount(client);

        int discountAmount = this.calculateBenefitsAmount(client);
        MenuList givenMenu = this.givenEvent(client);
        if (givenMenu.equals(CHAMPAGNE)) {
            discountAmount += CHAMPAGNE.getPrice();
        }

        return totalAmountBeforeDiscount - discountAmount;
    }

    /**
     * 주어진 client에게 배지를 부여한다.
     *
     * @param client 배지를 부여받을 client
     * @return 부여할 배지
     */
    public BadgeCategory awardBadge(Client client) {
        return BadgeCategory.getBadge(this.calculateBenefitsAmount(client));
    }

    /**
     * 주어진 admin이 이벤트의 총 참여 고객 수를 분석한다.
     *
     * @param admin 이벤트 총 참여 고객 수를 분석할 admin
     * @return 이벤트 총 참여 고객 수
     */
    public int analyzeTotalParticipants(Admin admin) {
        return admin.analyzeTotalParticipants(users.getClients());
    }

    /**
     * 주어진 admin이 다음 이벤트 예상 참여 고객 수를 추정한다.
     *
     * @param admin 다음 이벤트 예상 참여 고객 수를 추정할 admin
     * @return 다음 이벤트 예상 참여 고객 수
     */
    public double analyzeNextEventParticipants(Admin admin) {
        return admin.analyzeNextEventParticipants(users.getClients());
    }

    /**
     * 주어진 admin이 총 판매 금액을 계산한다.
     *
     * @param admin 총 판매 금액을 계산할 admin
     * @return 총 판매 금액
     */
    public long analyzeTotalEventOrders(Admin admin) {
        return admin.analyzeTotalEventOrders(users.getClients());
    }

    private void addDiscountIfApplicable(Map<EventCategory, Integer> benefits, EventCategory category, int discount) {
        if (discount != NONE_DISCOUNT.getDetails()) {
            benefits.put(category, discount);
        }
    }
}
