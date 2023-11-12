package christmas.model.validator;

import static christmas.util.exceptions.Exceptions.ORDERING_INVALID;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class OrderingValidatorTest {

    OrderingValidator orderingValidator;

    @BeforeEach
    void beforeEach() {
        orderingValidator = new OrderingValidator();
    }

    @ParameterizedTest
    @MethodSource("allOfTheMenusInList")
    @DisplayName("메뉴판에 있는 메뉴만을 입력한 경우 어떠한 예외도 반환되지 않는다.")
    void menuInList(Map<String, Integer> orders) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> orderingValidator.validateInMenuList(orders));
    }

    static Stream<Map<String, Integer>> allOfTheMenusInList() {
        return Stream.of(
                Map.of("양송이수프", 1, "타파스", 1, "시저샐러드", 1),
                Map.of("티본스테이크", 1, "바비큐립", 1, "해산물파스타", 1, "크리스마스파스타", 1),
                Map.of("초코케이크", 1, "아이스크림", 1),
                Map.of("제로콜라", 1, "레드와인", 1, "샴페인", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("someOfTheMenuNotInList")
    @DisplayName("메뉴판에 없는 메뉴를 하나라도 입력한 경우 예외가 반환된다.")
    void menuNotInList(Map<String, Integer> orders) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> orderingValidator.validateInMenuList(orders))
                .withMessage(ORDERING_INVALID.getMessage());
    }

    static Stream<Map<String, Integer>> someOfTheMenuNotInList() {
        return Stream.of(
                Map.of("피자", 1, "치킨", 1, "햄버거", 1),
                Map.of("제로콜라", 1, ", 피자", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("allOfTheNumberOfFoodsInRange")
    @DisplayName("메뉴의 주문 개수가 1 이상 20 이하인 경우 어떠한 예외도 반환되지 않는다.")
    void numberOfFoodsInRange(Map<String, Integer> orders) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> orderingValidator.validateNumberOfFoodsInRange(orders));
    }

    static Stream<Map<String, Integer>> allOfTheNumberOfFoodsInRange() {
        return Stream.of(
                Map.of("양송이수프", 1, "제로콜라", 20),
                Map.of("크리스마스파스타", 3, "티본스테이크", 5, "아이스크림", 15, "샴페인", 19)
        );
    }

    @ParameterizedTest
    @MethodSource("someNumberOfFoodsNotInRange")
    @DisplayName("메뉴의 주문 개수가 1 이상 20 이하가 아닌 경우 예외가 반환된다.")
    void numberOfFoodsNotInRange(Map<String, Integer> orders) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> orderingValidator.validateNumberOfFoodsInRange(orders))
                .withMessage(ORDERING_INVALID.getMessage());
    }

    static Stream<Map<String, Integer>> someNumberOfFoodsNotInRange() {
        return Stream.of(
                Map.of("시저샐러드", 0),
                Map.of("해산물파스타", 21)
        );
    }

    @ParameterizedTest
    @MethodSource("someOfTheMenuNotDrinks")
    @DisplayName("음료만 주문한 것이 아닌 경우 어떠한 예외도 반환되지 않는다.")
    void notOnlyDrinks(Map<String, Integer> orders) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> orderingValidator.validateNotOnlyBeverage(orders));
    }

    static Stream<Map<String, Integer>> someOfTheMenuNotDrinks() {
        return Stream.of(
                Map.of("양송이수프", 1),
                Map.of("티본스테이크", 1),
                Map.of("초코케이크", 1),
                Map.of("아이스크림", 1, "제로콜라", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("allOfTheMenuDrinks")
    @DisplayName("음료만 주문할 경우 예외가 반환된다.")
    void onlyDrinks(Map<String, Integer> orders) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> orderingValidator.validateNotOnlyBeverage(orders))
                .withMessage(ORDERING_INVALID.getMessage());
    }

    static Stream<Map<String, Integer>> allOfTheMenuDrinks() {
        return Stream.of(
                Map.of("제로콜라", 1),
                Map.of("레드와인", 1),
                Map.of("샴페인", 1),
                Map.of("제로콜라", 1, "레드와인", 1),
                Map.of("제로콜라", 1, "샴페인", 1),
                Map.of("레드와인", 1, "샴페인", 1),
                Map.of("제로콜라", 1, "레드와인", 1, "샴페인", 1)
        );
    }

    @ParameterizedTest
    @MethodSource("allOfTheTotalOrderAmountInRange")
    @DisplayName("주문 메뉴의 총 개수가 1개 이상 20개 이하인 경우 어떠한 예외도 반환되지 않는다.")
    void totalOrderAmountInRange(Map<String, Integer> orders) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> orderingValidator.validateTotalNumberOfFoodsInRange(orders));
    }

    static Stream<Map<String, Integer>> allOfTheTotalOrderAmountInRange() {
        return Stream.of(
                Map.of("양송이수프", 10, "티본스테이크", 9, "제로콜라", 1),
                Map.ofEntries(
                        Map.entry("양송이수프", 1),
                        Map.entry("타파스", 1),
                        Map.entry("시저샐러드", 1),
                        Map.entry("티본스테이크", 1),
                        Map.entry("바비큐립", 1),
                        Map.entry("해산물파스타", 1),
                        Map.entry("크리스마스파스타", 1),
                        Map.entry("초코케이크", 1),
                        Map.entry("아이스크림", 1),
                        Map.entry("제로콜라", 1),
                        Map.entry("레드와인", 1),
                        Map.entry("샴페인", 1)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("allOfTheTotalOrderAmountNotInRange")
    @DisplayName("주문 메뉴의 총 개수가 1개 이상 20개 미만이 아닌 경우 예외가 반환된다.")
    void totalOrderAmountNotInRange(Map<String, Integer> orders) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> orderingValidator.validateTotalNumberOfFoodsInRange(orders))
                .withMessage(ORDERING_INVALID.getMessage());
    }

    static Stream<Map<String, Integer>> allOfTheTotalOrderAmountNotInRange() {
        return Stream.of(
                Map.of("양송이수프", -1),
                Map.of("티본스테이크", 0),
                Map.ofEntries(
                        Map.entry("양송이수프", 2),
                        Map.entry("타파스", 2),
                        Map.entry("시저샐러드", 2),
                        Map.entry("티본스테이크", 2),
                        Map.entry("바비큐립", 2),
                        Map.entry("해산물파스타", 2),
                        Map.entry("크리스마스파스타", 2),
                        Map.entry("초코케이크", 2),
                        Map.entry("아이스크림", 2),
                        Map.entry("제로콜라", 2),
                        Map.entry("레드와인", 2),
                        Map.entry("샴페인", 2)
                )
        );
    }
}