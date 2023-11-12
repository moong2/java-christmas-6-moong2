package christmas.util.menu;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuUtils {
    public static Map<String, Integer> getAllMenus() {
        return Arrays.stream(MenuList.values())
                .collect(Collectors.toUnmodifiableMap(MenuList::getMenuName, MenuList::getPrice));
    }

    public static Map<String, Integer> getCategoryMenus(Category category) {
        return Arrays.stream(MenuList.values())
                .filter(menus -> menus.getCategory().equals(category))
                .collect(Collectors.toUnmodifiableMap(MenuList::getMenuName, MenuList::getPrice));
    }
}
