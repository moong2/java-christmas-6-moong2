package christmas.model.util.menu;

import static christmas.util.exceptions.Exceptions.ORDERING_INVALID;

/**
 * 주문 메뉴에 대한 기준을 나타내는 enum이다.
 */
public enum MenuDetails {
    MINIMUM_NUMBER_OF_FOOD(1),
    MAXIMUM_NUMBER_OF_FOOD(20);

    private final int detail;

    /**
     * MenuDetails 생성자다.
     *
     * @param detail 주문 메뉴에 대한 기준
     */
    MenuDetails(int detail) {
        this.detail = detail;
    }

    /**
     * 주문한 메뉴의 개수가 최소 기준을 만족하는지 확인한다.
     * 최소 기준을 만족하지 않을 경우, 예외를 던진다.
     *
     * @param numberOfFood 주문한 메뉴 개수
     * @throws IllegalArgumentException 주문한 메뉴 개수가 최소 기준을 만족하지 않을 경우
     */
    public static void checkMinimumOfTotalNumberOfFood(Integer numberOfFood) {
        if (numberOfFood < MINIMUM_NUMBER_OF_FOOD.detail) {
            throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
        }
    }

    /**
     * 주문한 메뉴의 개수가 최대 기준을 만족하는지 확인한다.
     * 최대 기준을 만족하지 않을 경우, 예외를 던진다.
     *
     * @param numberOfFood 주문한 메뉴 개수
     * @throws IllegalArgumentException 주문한 메뉴 개수가 최대 기준을 만족하지 않을 경우
     */
    public static void checkMaximumOfTotalNumberOfFood(Integer numberOfFood) {
        if (numberOfFood > MAXIMUM_NUMBER_OF_FOOD.detail) {
            throw new IllegalArgumentException(ORDERING_INVALID.getMessage());
        }
    }
}
