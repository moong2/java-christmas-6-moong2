package christmas.model.util.user;

import christmas.util.exceptions.UnauthorizedAccessException;

/**
 * User의 권한을 가지는 enum이다.
 */
public enum UserRoles {
    CLIENT,
    ADMIN;

    /**
     * 관리자 권한을 가지고 있는지 확인한다.
     * 관리자 권한을 가지고 있지 않을 경우 예외를 던진다.
     *
     * @param role 권한
     * @throws UnauthorizedAccessException 관리자 권한을 가지고 있지 않을 경우
     */
    public static void authenticateAdmin(UserRoles role) {
        if (!role.equals(ADMIN)) {
            throw new UnauthorizedAccessException();
        }
    }
}
