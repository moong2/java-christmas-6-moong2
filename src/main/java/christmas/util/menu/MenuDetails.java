package christmas.util.menu;

public enum MenuDetails {
    MINIMUM_NUMBER_OF_FOOD(1),
    MAXIMUM_NUMBER_OF_FOOD(20);

    private final int detail;

    MenuDetails(int detail) {
        this.detail = detail;
    }

    public int getDetail() {
        return detail;
    }
}
