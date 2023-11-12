package christmas.model.converter;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringToMapTest {

    StringToMap stringToMap;

    @BeforeEach
    void beforeEach() {
        stringToMap = new StringToMap();
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2,레드와인-1,초코케이크-1", "타파스-1,제로콜라-1", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"})
    @DisplayName("형식에 맞는 입력일 경우 어떠한 에러도 반환하지 않는다.")
    void formatSuccess(String order) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> stringToMap.toType(order));
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타,2", "해산물파스타:2,레드와인:1"})
    @DisplayName("형식에 맞지 않는 입력일 경우 에러를 반환한다.")
    void formatFailure(String order) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringToMap.toType(order));
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2147483647", "레드와인-0"})
    @DisplayName("메뉴가 Integer인 경우 어떠한 에러도 반환하지 않는다.")
    void numberOfFoodSuccess(String order) {
        // given
        // when & then
        assertThatNoException().isThrownBy(() -> stringToMap.toType(order));
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-number"})
    @DisplayName("메뉴가 Integer가 아닌 경우 에러를 반환한다.")
    void numberOfFoodFailure(String order) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringToMap.toType(order));
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-3,해산물파스타-3"})
    @DisplayName("중복된 메뉴를 입력한 경우 에러를 반환한다.")
    void duplicateFoodFailure(String order) {
        // given
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringToMap.toType(order));
    }
}