package christmas.model.util.user;

import static christmas.util.exceptions.Exceptions.AUTHENTICATION_INVALID;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import christmas.util.exceptions.UnauthorizedAccessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserRolesTest {

    @Test
    @DisplayName("User의 역할이 Admin일 경우 어떠한 예외도 반환하지 않는다.")
    void authenticateAdmin() {
        // given
        UserRoles role = UserRoles.ADMIN;

        // when & then
        assertThatNoException().isThrownBy(() -> UserRoles.authenticateAdmin(role));
    }

    @Test
    @DisplayName("User의 역할이 Client일 경우 UnauthorizedAccessException을 반환한다.")
    void authenticateClient() {
        // given
        UserRoles role = UserRoles.CLIENT;

        // when & then
        assertThatExceptionOfType(UnauthorizedAccessException.class)
                .isThrownBy(() -> UserRoles.authenticateAdmin(role))
                .withMessage(AUTHENTICATION_INVALID.getMessage());
    }
}