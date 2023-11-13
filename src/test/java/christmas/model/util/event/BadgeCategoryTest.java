package christmas.model.util.event;


import static christmas.model.util.event.BadgeCategory.NONE_BADGE;
import static christmas.model.util.event.BadgeCategory.SANTA_BADGE;
import static christmas.model.util.event.BadgeCategory.STAR_BADGE;
import static christmas.model.util.event.BadgeCategory.TREE_BADGE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BadgeCategoryTest {
    @ParameterizedTest
    @MethodSource("badge")
    @DisplayName("총혜택금에 따라 배지가 반환된다.")
    void badgeTest(int totalBenefit, BadgeCategory expectedBadge) {
        // given
        // when
        BadgeCategory testBadge = BadgeCategory.getBadge(totalBenefit);

        // then
        assertThat(testBadge).isEqualTo(expectedBadge);
    }

    static Stream<Arguments> badge() {
        return Stream.of(
                Arguments.of("0", NONE_BADGE),
                Arguments.of("4999", NONE_BADGE),
                Arguments.of("5000", STAR_BADGE),
                Arguments.of("9999", STAR_BADGE),
                Arguments.of("10000", TREE_BADGE),
                Arguments.of("19999", TREE_BADGE),
                Arguments.of("20000", SANTA_BADGE),
                Arguments.of("69860", SANTA_BADGE)
        );
    }
}