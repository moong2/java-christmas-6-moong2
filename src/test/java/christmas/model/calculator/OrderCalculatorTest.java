package christmas.model.calculator;

import static christmas.util.menu.MenuList.CHAMPAGNE;
import static christmas.util.menu.MenuList.NONE_MENU;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.util.menu.MenuList;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderCalculatorTest {

    private OrderCalculator orderCalculator;

    @BeforeEach
    void beforeEach() {
        orderCalculator = new OrderCalculator();
    }

    @ParameterizedTest
    @MethodSource("orders")
    @DisplayName("할인 전 총 주문 금액을 계산한다.")
    void calculateTotalOrderAmountBeforeDiscount(Map<String, Integer> orders, Map<Integer, Integer> testOrders) {
        // given
        int expectedAmount = testOrders.entrySet()
                .stream()
                .mapToInt((entry) -> entry.getKey() * entry.getValue())
                .sum();

        // when
        int testAmount = orderCalculator.totalOrderAmountBeforeDiscount(orders);

        // then
        assertThat(testAmount).isEqualTo(expectedAmount);
    }

    static Stream<Arguments> orders() {
        return Stream.of(
                Arguments.of(Map.of("시저샐러드", 1), Map.of(8000, 1)),
                Arguments.of(Map.of("양송이수프", 1, "바비큐립", 1, "초코케이크", 1, "제로콜라", 1), Map.of(6000, 1, 54000, 1, 15000, 1, 3000, 1)),
                Arguments.of(Map.of("타파스", 5, "티본스테이크", 5, "아이스크림", 5, "레드와인", 5), Map.of(5500, 5, 55000, 5, 5000, 5, 60000, 5))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {120000, 120001, 2147483647})
    @DisplayName("할인 전 총 주문 금액이 12만원 이상일 경우 샴페인을 반환한다.")
    void givenMenu(int totalOrderAmountBeforeDiscount) {
        // given
        MenuList given = CHAMPAGNE;

        // when & then
        assertThat(orderCalculator.givenMenu(totalOrderAmountBeforeDiscount)).isEqualTo(given);
    }

    @ParameterizedTest
    @ValueSource(ints = {119999, 0})
    @DisplayName("할인 전 총 주문 금액이 12만원 미만일 경우 NONE_MENU를 반환한다.")
    void notGivenMenu(int totalOrderAmountBeforeDiscount) {
        // given
        MenuList notGiven = NONE_MENU;

        // when & then
        assertThat(orderCalculator.givenMenu(totalOrderAmountBeforeDiscount)).isEqualTo(notGiven);
    }
}