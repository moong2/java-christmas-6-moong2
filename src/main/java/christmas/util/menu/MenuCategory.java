package christmas.util.menu;

public enum Category {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료"),
    NONE_MENU("존재하지 않는 메뉴");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
