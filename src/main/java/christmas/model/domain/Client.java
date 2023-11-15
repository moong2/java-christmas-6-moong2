package christmas.model.domain;

import christmas.model.util.user.UserRoles;

public class Client {
    private final VisitInformation visitInformation;
    private final Role role;

    private Client(VisitInformation visitInformation, Role role) {
        this.visitInformation = visitInformation;
        this.role = role;
    }

    public static Client of(VisitInformation visitInformation) {
        return new Client(visitInformation, new Role(UserRoles.CLIENT));
    }

    public VisitInformation getVisitInformation() {
        return this.visitInformation;
    }
}
