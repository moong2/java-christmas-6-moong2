package christmas.util.instructions;

/**
 * 12월 크리스마스 이벤트의 안내문구를 정의하는 enum이다.
 */
public enum Guidance {
    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),

    VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),

    PREVIEW_BENEFITS("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    
    PREVIEW_ORDER_MENU("<주문 메뉴>"),
    PREVIEW_EACH_MENU("%s %d개"),

    PREVIEW_TOTAL_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),

    PREVIEW_GIVE_MENU("<증정 메뉴>"),
    PREVIEW_GIVE_MENU_EXISTS("%s %d개"),

    PREVIEW_TOTAL_BENEFITS("<혜택 내역>"),
    PREVIEW_EACH_BENEFITS("%s 할인: %s원"),

    PREVIEW_TOTAL_BENEFITS_AMOUNT("<총혜택 금액>"),

    PREVIEW_TOTAL_AMOUNT_AFTER_DISCOUNT("<할인 후 예상 결제 금액>"),

    PREVIEW_EVENT_BADGE("<%d월 이벤트 배지>"),

    PREVIEW_EVENT_NOT_APPLY("없음"),
    PREVIEW_MONEY("%s원"),
    ;

    private final String guidance;

    /**
     * Guidance의 생성자다.
     *
     * @param guidance 안내문구
     */
    Guidance(String guidance) {
        this.guidance = guidance;
    }

    /**
     * 안내문구를 반환한다.
     *
     * @return String 형식의 안내문구
    */
    public String getGuidance() {
        return guidance;
    }
}
