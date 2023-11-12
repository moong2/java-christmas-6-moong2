package christmas.util.exceptions;

public enum Exceptions {
    DATE_OF_VISIT_INVALID("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ORDERING_INVALID("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    Exceptions(String message) {
        this.message = message;
    }

    public String getMessage() {
        StringBuilder message = new StringBuilder();
        String prefix = "[ERROR] ";

        message.append(prefix).append(this.message).append("\n");

        return message.toString();
    }
}
