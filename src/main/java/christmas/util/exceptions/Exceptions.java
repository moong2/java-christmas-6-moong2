package christmas.util.exceptions;

/**
 * 예외 출력문을 나타내는 enum이다.
 */
public enum Exceptions {
    DATE_OF_VISIT_INVALID("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ORDERING_INVALID("유효하지 않은 주문입니다. 다시 입력해 주세요."),

    AUTHENTICATION_INVALID("비정상적인 접근입니다."),

    EVENT_FULL("이벤트 참여자가 많아 참여할 수 없습니다."),
    ADMIN_FULL("관리자에 등록할 수 없습니다. 관계자에게 문의하세요.");

    private final String message;

    /**
     * Exceptions 생성자다.
     *
     * @param message 예외 메시지
     */
    Exceptions(String message) {
        this.message = message;
    }

    /**
     * 예외메시지를 반환한다.
     * 접미사인 '[ERROR] '를 포함하여 반환한다.
     *
     * @return 에러 메시지 형식을 맞춘 에러 메시지
     */
    public String getMessage() {
        StringBuilder message = new StringBuilder();
        String prefix = "[ERROR] ";

        message.append(prefix).append(this.message).append("\n");

        return message.toString();
    }
}
