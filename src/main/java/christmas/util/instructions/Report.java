package christmas.util.instructions;

public enum Report {
    HEADER_NUMBER_OF_CLIENTS("📄 이벤트 이용 고객 수"),

    HEADER_NUMBER_OF_NEXT_EVENT_CLIENTS("📄 설날 이벤트 예상 이용 고객 수"),

    STATISTIC_NUMBER_OF_CLIENTS("%s명"),

    HEADER_TOTAL_AMOUNT_OF_ORDERS("📄 총 판매 금액"),

    STATISTIC_TOTAL_AMOUNT_OF_ORDERS("%s원");

    private final String report;

    Report(String report) {
        this.report = report;
    }

    public String getReport() {
        return report;
    }
}
