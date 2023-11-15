package christmas.model.util.event;

import java.util.Arrays;

/**
 * Client의 총 혜택 금액에 따라 부여될 배지를 나타내는 enum이다. 각각의 배지는 수여받을 수 있는 기준 범위가 존재한다.
 */
public enum BadgeCategory {
    NONE_BADGE("없음", 0, 4999),
    STAR_BADGE("별", 5000, 9999),
    TREE_BADGE("트리", 10000, 19999),
    SANTA_BADGE("산타", 20000, 69860);;

    private final String category;
    private final int minimumStandard;
    private final int maximumStandard;

    /**
     * BadgeCategory의 생성자이다.
     *
     * @param category        배지의 이름
     * @param minimumStandard 배지를 받을 수 있는 최소 총 혜택 금액
     * @param maximumStandard 배지를 받을 수 있는 최대 총 혜택 금액
     */
    BadgeCategory(String category, int minimumStandard, int maximumStandard) {
        this.category = category;
        this.minimumStandard = minimumStandard;
        this.maximumStandard = maximumStandard;
    }

    /**
     * 배지의 이름을 반환한다.
     *
     * @return 배지의 이름
     */
    public String getCategory() {
        return category;
    }

    /**
     * 총 혜택 금액에 따라 적절한 배지를 결정한다.
     *
     * @param totalBenefit 총 혜택 금액
     * @return 총 헤택 금액에 맞는 배지
     */
    public static BadgeCategory getBadge(int totalBenefit) {
        return Arrays.stream(BadgeCategory.values())
                .filter((badge) -> totalBenefit >= badge.minimumStandard && totalBenefit <= badge.maximumStandard)
                .findFirst()
                .orElse(NONE_BADGE);
    }
}
