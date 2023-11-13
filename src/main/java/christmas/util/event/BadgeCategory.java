package christmas.util.event;

import java.util.Arrays;

public enum BadgeCategory {
    NONE_BADGE("없음", 0, 4999),
    STAR_BADGE("별", 5000, 9999),
    TREE_BADGE("트리", 10000, 19999),
    SANTA_BADGE("산타", 20000, 69860);;

    private final String category;
    private final int minimumStandard;
    private final int maximumStandard;

    BadgeCategory(String category, int minimumStandard, int maximumStandard) {
        this.category = category;
        this.minimumStandard = minimumStandard;
        this.maximumStandard = maximumStandard;
    }

    public static BadgeCategory getBadge(int totalBenefit) {
        return Arrays.stream(BadgeCategory.values())
                .filter((badge) -> totalBenefit >= badge.minimumStandard && totalBenefit <= badge.maximumStandard)
                .findFirst()
                .orElse(NONE_BADGE);
    }
}
