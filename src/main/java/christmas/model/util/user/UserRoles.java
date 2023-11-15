package christmas.model.util.user;

import christmas.util.exceptions.UnauthorizedAccessException;

public enum UserRoles {
    CLIENT,
    ADMIN;

    public static void authenticateAdmin(UserRoles role) {
        if (role.equals(CLIENT)) {
            throw new UnauthorizedAccessException();
        }
    }
}
