package christmas.model.util.menu;

import static christmas.util.exceptions.Exceptions.ORDERING_INVALID;

public enum MenuDetails {
    MINIMUM_NUMBER_OF_FOOD(1),
    MAXIMUM_NUMBER_OF_FOOD(20);

    private final int detail;

    MenuDetails(int detail) {
        this.detail = detail;
    }

    public static void checkMinimumOfTotalNumberOfFood(Integer numberOfFood) {
        if (numberOfFood < MINIMUM_NUMBER_OF_FOOD.detail) {
            throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
        }
    }

    public static void checkMaximumOfTotalNumberOfFood(Integer numberOfFood) {
        if (numberOfFood > MAXIMUM_NUMBER_OF_FOOD.detail) {
            throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
        }
    }
}
