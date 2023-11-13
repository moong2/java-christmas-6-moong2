package christmas.util.menu;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum MenuList {
    MUSHROOM_SOUP("양송이수프", 6_000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5_500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuCategory.APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000, MenuCategory.MAIN),
    BBQ_RIBS("바비큐립", 54_000, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuCategory.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000, MenuCategory.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuCategory.DESSERT),

    ZERO_COKE("제로콜라", 3_000, MenuCategory.BEVERAGE),
    RED_WINE("레드와인", 60_000, MenuCategory.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, MenuCategory.BEVERAGE),

    NONE_MENU("존재하지 않는 메뉴", 0, MenuCategory.NONE_MENU);

    private final String menuName;
    private final int price;
    private final MenuCategory menuCategory;

    MenuList(String menuName, int price, MenuCategory menuCategory) {
        this.menuName = menuName;
        this.price = price;
        this.menuCategory = menuCategory;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public int getPrice() {
        return this.price;
    }

    public MenuCategory getCategory() {
        return this.menuCategory;
    }

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
