package christmas.model.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsersTest {
    @Test
    @DisplayName("싱글톤 테스트")
    void singleTonTest() {
        // given
        Users first = Users.getInstance();
        Users second = Users.getInstance();

        // when & then
        assertThat(first).isEqualTo(second);
    }
}