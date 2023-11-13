package christmas.model.calculator;

import static christmas.util.menu.MenuList.CHAMPAGNE;
import static christmas.util.menu.MenuList.NONE_MENU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.util.menu.MenuList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
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
}