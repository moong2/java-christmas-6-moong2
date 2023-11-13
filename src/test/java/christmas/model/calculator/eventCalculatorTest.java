package christmas.model.calculator;

import static christmas.util.calendar.EventDetails.NONE_DISCOUNT;
import static christmas.util.calendar.EventDetails.SPECIAL_EVENT_DISCOUNT;
import static christmas.util.calendar.EventDetails.WEEKEND_WEEKDAY_EVENT_DISCOUNT;
import static christmas.util.menu.MenuList.CHAMPAGNE;
import static christmas.util.menu.MenuList.NONE_MENU;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.util.menu.MenuList;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class eventCalculatorTest {

    @ParameterizedTest
    @ValueSource(ints = {120000, 120001, 2147483647})
    @DisplayName("할인 전 총 주문 금액이 12만원 이상일 경우 샴페인을 반환한다.")
    void givenMenu(int totalOrderAmountBeforeDiscount) {
        // given
        MenuList given = CHAMPAGNE;

        // when & then
        assertThat(eventCalculator.givenMenu(totalOrderAmountBeforeDiscount)).isEqualTo(given);
    }

    @ParameterizedTest
    @ValueSource(ints = {119999, 0})
    @DisplayName("할인 전 총 주문 금액이 12만원 미만일 경우 NONE_MENU를 반환한다.")
    void notGivenMenu(int totalOrderAmountBeforeDiscount) {
        // given
        MenuList notGiven = NONE_MENU;

        // when & then
        assertThat(eventCalculator.givenMenu(totalOrderAmountBeforeDiscount)).isEqualTo(notGiven);
    }

    @ParameterizedTest
    @MethodSource("applyChristmasDDAYEvent")
    @DisplayName("크리스마스 디데이 할인이 적용될 경우 할인가가 반환된다.")
    void christmasDDayDiscountApply(LocalDate date, int expectedDiscount) {
        // given
        // when
        int testDiscount = eventCalculator.christmasDDayEvent(date);

        // then
        assertThat(testDiscount).isEqualTo(expectedDiscount);
    }

    static Stream<Arguments> applyChristmasDDAYEvent() {
        return Stream.of(
                Arguments.of(LocalDate.of(2023, 12, 1), 1000),
                Arguments.of(LocalDate.of(2023, 12, 15), 2400),
                Arguments.of(LocalDate.of(2023, 12, 25), 3400)
        );
    }

    @ParameterizedTest
    @MethodSource("notApplyChristmasDDAYEvent")
    @DisplayName("크리스마스 디데이 할인이 적용되지 않는 경우 0원이 반환된다.")
    void christmasDDayDiscountNotApply(LocalDate date) {
        // given
        // when
        int testDiscount = eventCalculator.christmasDDayEvent(date);

        // then
        assertThat(testDiscount).isEqualTo(0);
    }

    static Stream<LocalDate> notApplyChristmasDDAYEvent() {
        return Stream.of(
                LocalDate.of(2023, 12, 26),
                LocalDate.of(2023, 12, 31)
        );
    }

    @ParameterizedTest
    @MethodSource("ordersAndWeekday")
    @DisplayName("평일일 경우 평일 할인가가 반환된다.")
    void weekdayDiscountApply(Map<String, Integer> orders, LocalDate date, int expectedDiscount) {
        // given
        // when
        int testDiscount = eventCalculator.weekdayEvent(orders, date);

        // then
        assertThat(testDiscount).isEqualTo(expectedDiscount);
    }

    @ParameterizedTest
    @MethodSource("ordersAndWeekend")
    @DisplayName("평일이 아닐 경우 0원이 반환된다.")
    void weekdayDiscountNotApply(Map<String, Integer> orders, LocalDate date) {
        // given
        // when
        int testDiscount = eventCalculator.weekdayEvent(orders, date);

        // then
        assertThat(testDiscount).isEqualTo(NONE_DISCOUNT.getMoney());
    }

    @ParameterizedTest
    @MethodSource("ordersAndWeekend")
    @DisplayName("주말일 경우 주말 할인가가 반환된다.")
    void weekendDiscountApply(Map<String, Integer> orders, LocalDate date, int expectedDiscount) {
        // given
        // when
        int testDiscount = eventCalculator.weekendEvent(orders, date);

        // then
        assertThat(testDiscount).isEqualTo(expectedDiscount);
    }

    @ParameterizedTest
    @MethodSource("ordersAndWeekday")
    @DisplayName("주말이 아닐 경우 0원이 반환된다.")
    void weekendDiscountNotApply(Map<String, Integer> orders, LocalDate date) {
        // given
        // when
        int testDiscount = eventCalculator.weekendEvent(orders, date);

        // then
        assertThat(testDiscount).isEqualTo(NONE_DISCOUNT.getMoney());
    }

    static Stream<Arguments> ordersAndWeekday() {
        return Stream.of(
                Arguments.of(Map.of("양송이수프", 1, "초코케이크", 1), LocalDate.of(2023, 12, 4),
                        WEEKEND_WEEKDAY_EVENT_DISCOUNT.getMoney()),
                Arguments.of(Map.of("초코케이크", 2, "아이스크림", 3), LocalDate.of(2023, 12, 25),
                        WEEKEND_WEEKDAY_EVENT_DISCOUNT.getMoney() * 5),
                Arguments.of(Map.of("티본스테이크", 1, "레드와인", 1), LocalDate.of(2023, 12, 31),
                        WEEKEND_WEEKDAY_EVENT_DISCOUNT.getMoney() * 0)
        );
    }

    static Stream<Arguments> ordersAndWeekend() {
        return Stream.of(
                Arguments.of(Map.of("양송이수프", 1, "티본스테이크", 1), LocalDate.of(2023, 12, 1),
                        WEEKEND_WEEKDAY_EVENT_DISCOUNT.getMoney() * 1),
                Arguments.of(Map.of("초코케이크", 2, "아이스크림", 3), LocalDate.of(2023, 12, 15),
                        WEEKEND_WEEKDAY_EVENT_DISCOUNT.getMoney() * 0),
                Arguments.of(Map.of("티본스테이크", 2, "크리스마스파스타", 3), LocalDate.of(2023, 12, 30),
                        WEEKEND_WEEKDAY_EVENT_DISCOUNT.getMoney() * 5)
        );
    }

    @ParameterizedTest
    @MethodSource("specialDate")
    @DisplayName("특별 할인이 적용될 경우 할인가가 반환된다.")
    void specialDiscountApply(LocalDate date) {
        // given
        // when
        int testDiscount = eventCalculator.specialEvent(date);

        // then
        assertThat(testDiscount).isEqualTo(SPECIAL_EVENT_DISCOUNT.getMoney());
    }

    static Stream<Arguments> specialDate() {
        return Stream.of(
                Arguments.of(LocalDate.of(2023, 12, 3)),
                Arguments.of(LocalDate.of(2023, 12, 10)),
                Arguments.of(LocalDate.of(2023, 12, 17)),
                Arguments.of(LocalDate.of(2023, 12, 24)),
                Arguments.of(LocalDate.of(2023, 12, 25)),
                Arguments.of(LocalDate.of(2023, 12, 31))
        );
    }

    @ParameterizedTest
    @MethodSource("notSpecialDate")
    @DisplayName("특별 할인이 적용되지 않는 경우 0원이 반환된다.")
    void specialDiscountNotApply(LocalDate date) {
        // given
        // when
        int testDiscount = eventCalculator.specialEvent(date);

        // then
        assertThat(testDiscount).isEqualTo(NONE_DISCOUNT.getMoney());
    }

    static Stream<Arguments> notSpecialDate() {
        return Stream.of(
                Arguments.of(LocalDate.of(2023, 12, 1)),
                Arguments.of(LocalDate.of(2023, 12, 4)),
                Arguments.of(LocalDate.of(2023, 12, 26)),
                Arguments.of(LocalDate.of(2023, 12, 29)),
                Arguments.of(LocalDate.of(2023, 12, 30))
        );
    }
}
