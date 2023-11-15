package christmas.model.calculator;

import christmas.model.domain.Client;
import java.util.List;

/**
 * StatisticCalculator는 관리자가 역할인 이벤트 통계 데이터를 계산하는 책임이 있다. 이벤트에 참여한 총 고객 수와 다음 설날 이벤트의 예상 재참여 고객 수, 총 판매금액을 계산한다.
 */
public class StatisticCalculator {

    private static final int ANTICIPATE_PERCENT = 5;
    private static final double PERCENT = 100.0;
    private static final double ROUND = 100.0;

    /**
     * 이벤트에 참여한 총 고객 수를 계산한다.
     *
     * @param clients 이벤트에 참여한 고객들
     * @return integer 형식의 이벤트 참여 고객 수
     */
    public static int numberOfClients(List<Client> clients) {
        return clients.size();
    }

    /**
     * 주어진 총 고객 수를 기반으로 다음 설날 이벤트 예상 재참여 고객 수를 계산한다. 총 고객 수의 5%가 다음 설날 이벤트 예상 재참여 고객 수이다.
     *
     * @param clients 이벤트에 참여한 고객들
     * @return double 형식의 다음 설날 이베트 예상 재참여 고객 수
     */
    public static double nextEventNumberOfClients(List<Client> clients) {
        int numberOfClients = numberOfClients(clients);
        double nextEventNumberOfClients = numberOfClients * (ANTICIPATE_PERCENT / PERCENT);

        return Math.round(nextEventNumberOfClients * ROUND) / ROUND;
    }

    /**
     * 주어진 고객들을 기반으로 총 판매 금액을 계산한다. 고객들 각각의 할인 전 총 주문금액을 더해 반환한다.
     *
     * @param clients 이벤트에 참여한 고객들
     * @return long 형식의 총 판매 금액
     */
    public static long totalSalesAmount(List<Client> clients) {
        long totalSalesAmount = 0L;

        for (Client client : clients) {
            totalSalesAmount += client.getTotalAmountBeforeDiscount();
        }

        return totalSalesAmount;
    }
}
