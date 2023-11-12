package christmas.util.menuList;

import java.util.Map;

public enum Category {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public Map<String, Integer> getAllMenus() {

    }
}
