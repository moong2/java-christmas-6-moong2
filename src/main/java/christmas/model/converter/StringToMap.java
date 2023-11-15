package christmas.model.converter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * StringToMap은 StringConverter의 인터페이스의 구현부이다. String을 String과 Integer를 가지는 Map으로 변환하기 위해 특정 구분점을 기준으로 구분한다. String은 메뉴
 * 이름을 저장하고, Integer는 주문한 메뉴 개수를 저장한다.
 */
public class StringToMap implements StringConverter<Map<String, Integer>> {
    private static final String DELIMITER_MENUS = ",";
    private static final String DELIMITER_MENU_NAME_AND_NUMBER = "-";
    private static final Integer LIMIT = -1;
    private static final Integer ONE_MENU_ORDER_SIZE = 2;

    /**
     * 메뉴 이름과 주문한 개수를 가지는 Map으로 변환한다. 입력값인 문자열이 특정 형식으로 입력될 것을 기대하며, 메뉴 이름과 주문한 개수는 특정 구분점을 기준으로 입력된다.
     *
     * @param value 변환될 문자열
     * @return 메뉴 이름과 주문한 개수를 표현한 Map
     * @throws IllegalArgumentException 주어진 문자열의 형식이 기대한 바와 다르거나, 중복된 메뉴 이름이 들어왔거나, 주문한 메뉴의 개수가 Integer 형식이 아닐 경우
     */
    @Override
    public Map<String, Integer> toType(String value) {
        Map<String, Integer> orderMenus = new LinkedHashMap<String, Integer>();

        for (String splitValue : value.split(DELIMITER_MENUS, LIMIT)) {
            String[] menuNameAndNumberOfFood = splitValue.split(DELIMITER_MENU_NAME_AND_NUMBER, LIMIT);
            checkFormat(menuNameAndNumberOfFood);

            String menuName = menuNameAndNumberOfFood[0];
            checkMenuName(orderMenus, menuName);

            String numberOfFood = menuNameAndNumberOfFood[1];
            Integer convertedNumberOfFood = checkNumberOfFood(numberOfFood);

            orderMenus.put(menuName, convertedNumberOfFood);
        }

        return Collections.unmodifiableMap(orderMenus);
    }

    private void checkFormat(String[] menuNameAndNumberOfFood) {
        if (menuNameAndNumberOfFood.length != ONE_MENU_ORDER_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void checkMenuName(Map<String, Integer> orderMenus, String menuName) {
        if (orderMenus.containsKey(menuName)) {
            throw new IllegalArgumentException();
        }
    }

    private Integer checkNumberOfFood(String numberOfFood) {
        try {
            return Integer.parseInt(numberOfFood);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
