package christmas.util.instructions;

/**
 * 고객에게 안내할 이벤트 주의 사항을 담은 enum이다.
 */
public enum Precautions {
    TOTAL_ORDER_AMOUNT("총 주문 금액 10,000원 이상부터 이벤트가 적용됩니다."),
    ONLY_DRINK("음료만 주문 시, 주문할 수 없습니다."),
    TOTAL_NUMBER_OF_MENUS(
            "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. (e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)");

    private final String description;

    /**
     * Precautions 생성자다.
     *
     * @param description 주의 사항
     */
    Precautions(String description) {
        this.description = description;
    }

    /**
     * 주의 사항을 반환한다.
     *
     * @return String 형식의 주의 사항
     */
    public String getDescription() {
        return description;
    }

    /**
     * 주의 사항을 형식에 맞게 반환한다. 다른 사항들과 구분되는 구분선이 존재한다. 유저들의 주의를 환기시키기 위한 접두사를 추가한다.
     *
     * @return 형식에 맞춘 주의 사항
     */
    public static String getPrecautions() {
        StringBuilder printPrecautions = new StringBuilder();
        String prefix = "[💡] ";
        String start = "--------12월 이벤트 주의 사항--------\n";
        String end = "---------------------------------\n";

        printPrecautions.append(start);
        for (Precautions precaution : values()) {
            printPrecautions.append(prefix).append(precaution.getDescription()).append("\n");
        }
        printPrecautions.append(end);

        return printPrecautions.toString();
    }
}
