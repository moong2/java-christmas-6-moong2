package christmas.model.domain;

import christmas.model.util.user.UserRoles;

/**
 * User의 권한을 저장한다.
 */
public record Role(UserRoles userRole) {
}
