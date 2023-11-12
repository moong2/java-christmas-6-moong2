package christmas.util.menuList;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuUtils {
    public static Map<String, Integer> getAllMenus() {
        return Arrays.stream(Menu.values())
                .collect(Collectors.toUnmodifiableMap(Menu::getMenuName, Menu::getPrice));
    }
}
