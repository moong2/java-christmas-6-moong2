package christmas.util.menu;

import static christmas.util.menu.MenuList.NONE_MENU;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuUtils {
    public static Map<String, Integer> getAllMenus() {
        return Arrays.stream(MenuList.values())
                .collect(Collectors.toUnmodifiableMap(MenuList::getMenuName, MenuList::getPrice));
    }

    public static Map<String, Integer> getCategoryMenus(MenuCategory menuCategory) {
        return Arrays.stream(MenuList.values())
                .filter(menus -> menus.getCategory().equals(menuCategory))
                .collect(Collectors.toUnmodifiableMap(MenuList::getMenuName, MenuList::getPrice));
    }

    public static MenuList getMenuByName(String menuName) {
        return Arrays.stream(MenuList.values())
                .filter(menuItem -> menuItem.getMenuName().equals(menuName))
                .findFirst()
                .orElse(NONE_MENU);
    }
}
