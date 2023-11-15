package christmas.model.util.menu;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 메뉴판을 나타내는 enum이다.
 */
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

    /**
     * MenuList 생성자다.
     *
     * @param menuName     메뉴 이름
     * @param price        메뉴의 가격
     * @param menuCategory 메뉴의 종류
     */
    MenuList(String menuName, int price, MenuCategory menuCategory) {
        this.menuName = menuName;
        this.price = price;
        this.menuCategory = menuCategory;
    }

    /**
     * 메뉴 이름을 반환한다.
     *
     * @return 메뉴 이름
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * 메뉴 가격을 반환한다.
     *
     * @return 메뉴 가격
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * 메뉴의 종류를 반환한다.
     *
     * @return 메뉴의 종류
     */
    public MenuCategory getCategory() {
        return this.menuCategory;
    }

    /**
     * 메뉴판의 전체 메뉴를 반환한다. 메뉴 이름과 메뉴의 가격을 담아 변경 불가능한 Map으로 반환한다.
     *
     * @return 메뉴 이름을 담는 String, 메뉴 가격을 담는 Integer를 가지고 있는 변경 불가능한 Map
     */
    public static Map<String, Integer> getAllMenus() {
        return Arrays.stream(MenuList.values())
                .collect(Collectors.toUnmodifiableMap(MenuList::getMenuName, MenuList::getPrice));
    }

    /**
     * 해당 메뉴 종류의 메뉴들을 반환한다. 해당 메뉴 종류에 있는 메뉴의 이름과 가격을 담아 변경 불가능한 Map으로 반환한다.
     *
     * @param menuCategory 메뉴 종류
     * @return 메뉴 이름을 담는 String, 메뉴 가격을 담는 Integer를 가지고 있는 변경 불가능한 Map
     */
    public static Map<String, Integer> getCategoryMenus(MenuCategory menuCategory) {
        return Arrays.stream(MenuList.values())
                .filter(menus -> menus.getCategory().equals(menuCategory))
                .collect(Collectors.toUnmodifiableMap(MenuList::getMenuName, MenuList::getPrice));
    }

    /**
     * 메뉴의 이름으로 메뉴 이름, 가격, 메뉴의 종류를 알 수 있는 메뉴 객체를 반환한다. 해당 메뉴가 없을 경우 NONE_MENU를 반환한다.
     *
     * @param menuName 찾으려는 메뉴의 이름
     * @return 메뉴판에 찾으려는 메뉴가 있다면 메뉴의 정보를, 없다면 NONE_MENu를 반환한다.
     */
    public static MenuList getMenuByName(String menuName) {
        return Arrays.stream(MenuList.values())
                .filter(menuItem -> menuItem.getMenuName().equals(menuName))
                .findFirst()
                .orElse(NONE_MENU);
    }
}
