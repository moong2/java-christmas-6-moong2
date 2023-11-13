package christmas.util.menu;

import static christmas.util.menu.MenuList.CAESAR_SALAD;
import static christmas.util.menu.MenuList.CHOCOLATE_CAKE;
import static christmas.util.menu.MenuList.CHRISTMAS_PASTA;
import static christmas.util.menu.MenuList.NONE_MENU;
import static christmas.util.menu.MenuList.RED_WINE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuUtilsTest {
    @Test
    @DisplayName("모든 메뉴를 가져온다.")
    void getAllMenus() {
        // given
        Map<String, Integer> allMenus = new HashMap<String, Integer>();

        for (MenuList menu : MenuList.values()) {
            allMenus.put(menu.getMenuName(), menu.getPrice());
        }

        // when
        Map<String, Integer> testMenus = MenuUtils.getAllMenus();

        // then
        assertThat(testMenus).isEqualTo(allMenus);
    }

    @ParameterizedTest
    @MethodSource("getAllCategories")
    @DisplayName("특정 카테고리의 메뉴를 가져온다.")
    void getCategoryMenu(MenuCategory menuCategory) {
        // given
        HashMap<String, Integer> categoryMenus = new HashMap<>();

        for (MenuList menu : MenuList.values()) {
            if (menu.getCategory().equals(menuCategory)) {
                categoryMenus.put(menu.getMenuName(), menu.getPrice());
            }
        }

        // when
        Map<String, Integer> testMenus = MenuUtils.getCategoryMenus(menuCategory);

        // then
        assertThat(testMenus).isEqualTo(categoryMenus);
    }

    private static Stream<MenuCategory> getAllCategories() {
        return Stream.of(MenuCategory.values());
    }

    @ParameterizedTest
    @MethodSource("getSpecificFood")
    @DisplayName("특정 이름의 메뉴를 가져온다.")
    void getMenuByName(String menuName, MenuList expectedMenu) {
        // given
        // when
        MenuList testMenu = MenuUtils.getMenuByName(menuName);

        // then
        assertThat(testMenu).isEqualTo(expectedMenu);
    }

    private static Stream<Arguments> getSpecificFood() {
        return Stream.of(
                Arguments.of("시저샐러드", CAESAR_SALAD),
                Arguments.of("크리스마스파스타", CHRISTMAS_PASTA),
                Arguments.of("초코케이크", CHOCOLATE_CAKE),
                Arguments.of("레드와인", RED_WINE),
                Arguments.of("피자", NONE_MENU)
        );
    }
}