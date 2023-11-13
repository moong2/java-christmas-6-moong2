package christmas.model.calculator;

import static christmas.model.util.event.EventCategory.CHRISTMAS_DDAY_EVENT;
import static christmas.model.util.event.EventCategory.GIVE_EVENT;
import static christmas.model.util.event.EventCategory.SPECIAL_EVENT;
import static christmas.model.util.event.EventCategory.WEEKDAY_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.util.event.EventCategory;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

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
    @MethodSource("totalOrderAmountAfterDiscount")
    @DisplayName("할인 내역을 통해 할인 금액을 반환한다.")
    void calculateTotalOrderAmountAfterDiscount(Map<EventCategory, Integer> discounts, int totalOrderAmount) {
        // given
        // when
        int testTotalOrderAmount = orderCalculator.totalDiscount(discounts);

        // then
        assertThat(testTotalOrderAmount).isEqualTo(totalOrderAmount);
    }

    static Stream<Arguments> totalOrderAmountAfterDiscount() {
        return Stream.of(
                Arguments.of(Map.of(CHRISTMAS_DDAY_EVENT.getCategory(), 1200, WEEKDAY_EVENT.getCategory(), 4046,
                        SPECIAL_EVENT.getCategory(), 1000), 6246)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "142000, 6246, 135754"
    })
    @DisplayName("총 할인 금액을 통해 할인 후 예상 결제 금액을 반환한다.")
    void calculateTotalOrderAmountAfterDiscount(int totalOrderAmountBeforeDiscount, int discount, int expectedTotalOrderAmountAfterDiscount) {
        // given
        // when
        int testTotalOrderAmountAfterDiscount = orderCalculator.totalOrderAmountAfterDiscount(totalOrderAmountBeforeDiscount, discount);

        // then
        assertThat(testTotalOrderAmountAfterDiscount).isEqualTo(expectedTotalOrderAmountAfterDiscount);
    }

    @ParameterizedTest
    @MethodSource("totalBenefits")
    @DisplayName("혜택 내역(증정 메뉴 + 할인 내역)에 따라 총혜택 금액이 반환된다.")
    void totalBenefitsTest(Map<EventCategory, Integer> discounts, int totalDiscount) {
        // given
        // when
        int testTotalDiscount = orderCalculator.totalDiscount(discounts);

        // then
        assertThat(testTotalDiscount).isEqualTo(totalDiscount);
    }

    static Stream<Arguments> totalBenefits() {
        return Stream.of(
                Arguments.of(Map.of(CHRISTMAS_DDAY_EVENT.getCategory(), 1200, WEEKDAY_EVENT.getCategory(), 4046,
                        SPECIAL_EVENT.getCategory(), 1000, GIVE_EVENT.getCategory(), 25000), 31246)
        );
    }
}