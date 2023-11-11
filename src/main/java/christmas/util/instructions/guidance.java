package christmas.util.instructions;

public enum guidance {
    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

    private final String guidance;

    guidance(String guidance) {
        this.guidance = guidance;
    }

    public String getGuidance() {
        return guidance;
    }
}
