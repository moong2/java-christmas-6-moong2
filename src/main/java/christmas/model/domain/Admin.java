package christmas.model.domain;

import christmas.model.calculator.StatisticCalculator;
import christmas.model.util.user.UserRoles;
import christmas.util.exceptions.UnauthorizedAccessException;

/**
 * 크리스마스 이벤트의 관리자를 나타낸다. 이벤트와 관련된 데이터를 계산하는 함수를 포함한다. 분석을 위해 StatisticCalculator를 이용한다.
 */
public class Admin {
    private final Role role;

    private Admin() {
        role = new Role(UserRoles.ADMIN);
    }

    /**
     * 새 admin을 만드는 팩토리 메소드이다.
     *
     * @return
     */
    public static Admin of() {
        return new Admin();
    }

    /**
     * 이벤트 총 참여 고객 수를 분석하고 반환한다. 이 메소드는 관리자 권한을 요구한다.
     *
     * @param clients 이벤트에 참여한 고객들
     * @return integer 형식의 이벤트에 참여한 총 고객 수
     * @throws UnauthorizedAccessException 관리자 권한이 아닐 경우
     */
    public int analyzeTotalParticipants(Clients clients) {
        UserRoles.authenticateAdmin(role.userRole());

        return StatisticCalculator.numberOfClients(clients.getClients());
    }

    /**
     * 다음 설날 이벤트에 재참여할 예상 고객 수를 분석하고 추정한다. 이 메소드는 관리자 권한을 요구한다.
     *
     * @param clients 이벤트에 참여한 고객들
     * @return double 형식의 이벤트에 재참여할 고객들
     * @throws UnauthorizedAccessException 관리자 권한이 아닐 경우
     */
    public double analyzeNextEventParticipants(Clients clients) {
        UserRoles.authenticateAdmin(role.userRole());

        return StatisticCalculator.nextEventNumberOfClients(clients.getClients());
    }

    /**
     * 총 판매금액을 계산하고 반환한다.
     *
     * @param clients 이벤트에 참여한 고객들
     * @return 총 판매금액
     * @throws UnauthorizedAccessException 관리자 권한이 아닐 경우
     */
    public long analyzeTotalEventOrders(Clients clients) {
        UserRoles.authenticateAdmin(role.userRole());

        return StatisticCalculator.totalSalesAmount(clients.getClients());
    }
}
