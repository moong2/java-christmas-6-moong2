package christmas.model.util.menu;

/**
 * 메뉴의 종류를 나타내는 enum이다.
 */
public enum MenuCategory {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료"),
    NONE_MENU("존재하지 않는 메뉴");

    private final String category;

    /**
     * MenuCategory의 생성자다.
     *
     * @param category 메뉴 종류
     */
    MenuCategory(String category) {
        this.category = category;
    }
}
