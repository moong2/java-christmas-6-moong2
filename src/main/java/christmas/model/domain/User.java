package christmas.model.domain;

import christmas.model.util.user.UserRoles;

public class User {
    private final VisitInformation visitInformation;
    private final Role role;

    private User(VisitInformation visitInformation, Role role) {
        this.visitInformation = visitInformation;
        this.role = role;
    }

    public static User userOf(VisitInformation visitInformation) {
        return new User(visitInformation, new Role(UserRoles.CLIENT));
    }
}
