package christmas.util.instructions;

public enum Precautions {
    TOTAL_ORDER_AMOUNT("총 주문 금액 10,000원 이상부터 이벤트가 적용됩니다."),
    ONLY_DRINK("음료만 주문 시, 주문할 수 없습니다."),
    TOTAL_NUMBER_OF_MENUS(
            "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. (e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)");
    private final String description;

    Precautions(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getPrecautions() {
        StringBuilder precautions = new StringBuilder();
        String prefix = "[💡] ";
        String start = "--------12월 이벤트 주의 사항--------\n";
        String end = "---------------------------------\n";

        precautions.append(start);
        for (Precautions precaution : values()) {
            precautions.append(prefix).append(precaution.getDescription()).append("\n");
        }
        precautions.append(end);

        return precautions.toString();
    }
    }
