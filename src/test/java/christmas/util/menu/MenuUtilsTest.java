package christmas.util.menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
    void getCategoryMenu(Category category) {
        // given
        HashMap<String, Integer> categoryMenus = new HashMap<>();

        for (MenuList menu : MenuList.values()) {
            if (menu.getCategory().equals(category)) {
                categoryMenus.put(menu.getMenuName(), menu.getPrice());
            }
        }

        // when
        Map<String, Integer> testMenus = MenuUtils.getCategoryMenus(category);

        // then
        assertThat(testMenus).isEqualTo(categoryMenus);
    }

    private static Stream<Category> getAllCategories() {
        return Stream.of(Category.values());
    }
}