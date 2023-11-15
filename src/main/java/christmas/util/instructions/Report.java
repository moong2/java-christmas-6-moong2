package christmas.util.instructions;

/**
 * Admin이 이벤트를 관리하기 위한 통계 자료의 출력 형식을 담은 enum이다.
 */
public enum Report {
    HEADER_NUMBER_OF_CLIENTS("📄 이벤트 이용 고객 수"),

    HEADER_NUMBER_OF_NEXT_EVENT_CLIENTS("📄 설날 이벤트 예상 이용 고객 수"),

    STATISTIC_NUMBER_OF_CLIENTS("%s명"),

    HEADER_TOTAL_AMOUNT_OF_ORDERS("📄 총 판매 금액"),

    STATISTIC_TOTAL_AMOUNT_OF_ORDERS("%s원");

    private final String report;

    /**
     * Report 생성자다.
     *
     * @param report 통계 자료의 출력 형식
     */
    Report(String report) {
        this.report = report;
    }

    /**
     * 통계 자료의 출력 형식을 반환한다.
     *
     * @return 통계 자료의 출력 형식
     */
    public String getReport() {
        return report;
    }
}
