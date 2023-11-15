package christmas.model.calculator;

import static christmas.model.util.event.EventPeriod.EVENT_MONTH;
import static christmas.model.util.event.EventPeriod.EVENT_YEAR;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.domain.Client;
import christmas.model.domain.Orders;
import christmas.model.domain.VisitInformation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class statisticCalculatorTest {
    private static final int ANTICIPATE_PERCENT = 5;
    private static final double PERCENT = 100.0;
    private static final double ROUND = 100.0;

    StatisticCalculator statisticCalculator;

    @BeforeEach
    void beforeEach() {
        statisticCalculator = new StatisticCalculator();
    }

    @ParameterizedTest
    @MethodSource("clientsAndSize")
    @DisplayName("12월 이벤트 참여 유저 수를 계산한다.")
    void calculateNumberOfClients(List<Client> clients, int expectedSize) {
        // given
        // when
        int testSize = statisticCalculator.numberOfClients(clients);

        // then
        assertThat(testSize).isEqualTo(expectedSize);
    }

    static Stream<Arguments> clientsAndSize() {
        return Stream.of(
                Arguments.of(zero_clients(), 0),
                Arguments.of(three_clients(), 3),
                Arguments.of(fifty_clients(), 50)
        );
    }

    @ParameterizedTest
    @MethodSource("clientsAndNextEventSize")
    @DisplayName("설날 이벤트에 재참여할 것이라 기대되는 유저 수를 계산한다.")
    void nextEventNumberOfClients(List<Client> clients, double expectedSize) {
        // given
        // when
        double testSize = statisticCalculator.nextEventNumberOfClients(clients);

        // then
        assertThat(testSize).isEqualTo(expectedSize);
    }

    static Stream<Arguments> clientsAndNextEventSize() {
        return Stream.of(
                Arguments.of(zero_clients(), Math.round(0 * ANTICIPATE_PERCENT / PERCENT * ROUND) / ROUND),
                Arguments.of(three_clients(), Math.round(3 * ANTICIPATE_PERCENT / PERCENT * ROUND) / ROUND),
                Arguments.of(fifty_clients(), Math.round(50 * ANTICIPATE_PERCENT / PERCENT * ROUND) / ROUND)
        );
    }

    @ParameterizedTest
    @MethodSource("totalEventOrders")
    @DisplayName("총 판매금액을 계산한다.")
    void totalSalesAmount(List<Client> clients, long expectedTotalSalesAmount) {
        // given
        // when
        long testTotalSalesAmount = statisticCalculator.totalSalesAmount(clients);

        // then
        assertThat(testTotalSalesAmount).isEqualTo(expectedTotalSalesAmount);
    }

    static Stream<Arguments> totalEventOrders() {
        return Stream.of(
                Arguments.of(zero_clients(), 0),
                Arguments.of(three_clients(), 9000 + 170000 + 821500),
                Arguments.of(fifty_clients(), 450000)
        );
    }


    static List<Client> zero_clients() {
        return List.of();
    }

    static List<Client> three_clients() {

        LocalDate visitDate = LocalDate.of(EVENT_YEAR.getDate(), EVENT_MONTH.getDate(), 25);

        Client client1 = Client.of(VisitInformation.of(visitDate, Orders.of(Map.of("양송이수프", 1, "제로콜라", 1))));
        Client client2 = Client.of(VisitInformation.of(visitDate, Orders.of(Map.of("티본스테이크", 3, "아이스크림", 1))));
        Client client3 = Client.of(VisitInformation.of(visitDate,
                Orders.of(Map.of("타파스", 3, "해산물파스타", 5, "초코케이크", 2, "레드와인", 10))));

        return List.of(client1, client2, client3);
    }

    static List<Client> fifty_clients() {
        LocalDate visitDate = LocalDate.of(EVENT_YEAR.getDate(), EVENT_MONTH.getDate(), 25);

        Client client = Client.of(VisitInformation.of(visitDate, Orders.of(Map.of("양송이수프", 1, "제로콜라", 1))));

        List<Client> clients = new ArrayList<>();
        for (int num = 0; num < 50; num++) {
            clients.add(client);
        }

        return clients;
    }
}