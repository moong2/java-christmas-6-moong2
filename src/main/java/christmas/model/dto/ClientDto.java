package christmas.model.dto;

import christmas.model.domain.Role;
import christmas.model.domain.Client;
import christmas.model.domain.VisitInformation;
import christmas.model.util.user.UserRoles;

public record ClientDto(
        VisitInformation visitInformation,
        Role role
) {

    public ClientDto(VisitInformation visitInformation, Role role) {
        this.visitInformation = visitInformation;
        this.role = role;
    }

    public static ClientDto from(Client client) {
        return new ClientDto(client.getVisitInformation(), new Role(UserRoles.CLIENT));
    }
}
