package christmas.model.converter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StringToMap implements StringConverter<Map<String, Integer>> {
    private static final String DELIMITER_MENUS = ",";
    private static final String DELIMITER_MENU_NAME_AND_PRICE = "-";
    private static final Integer LIMIT = -1;
    private static final Integer ONE_MENU_ORDER_SIZE = 2;

    @Override
    public Map<String, Integer> toType(String value) {
        Map<String, Integer> orderMenus =  new LinkedHashMap<String, Integer>();

        for (String splitValue : value.split(DELIMITER_MENUS, LIMIT)) {
            String[] menuNameAndPrice = splitValue.split(DELIMITER_MENU_NAME_AND_PRICE, LIMIT);
            checkFormat(menuNameAndPrice);

            String menuName = menuNameAndPrice[0];
            checkMenuName(orderMenus, menuName);

            String price = menuNameAndPrice[1];
            Integer convertedPrice = checkPrice(price);

            orderMenus.put(menuName, convertedPrice);
        }

        return Collections.unmodifiableMap(orderMenus);
    }

    private void checkFormat(String[] menuNameAndPrice) {
        if (menuNameAndPrice.length != ONE_MENU_ORDER_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void checkMenuName(Map<String, Integer> orderMenus, String menuName) {
        if (orderMenus.containsKey(menuName)) {
            throw new IllegalArgumentException();
        }
    }

    private Integer checkPrice(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
