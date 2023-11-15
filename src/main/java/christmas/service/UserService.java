package christmas.service;

import static christmas.model.util.event.EventPeriod.EVENT_MONTH;
import static christmas.model.util.event.EventPeriod.EVENT_YEAR;

import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.model.domain.Orders;
import christmas.model.domain.Users;
import christmas.model.domain.VisitInformation;
import christmas.model.dto.VisitInformationDto;
import java.time.LocalDate;

public class UserService {
    private final Users users = Users.getInstance();

    public Client addClient(VisitInformationDto visitInformationDto) {
        Client client = Client.of(createVisitInformation(visitInformationDto));
        users.addUser(client);

        return client;
    }

    private VisitInformation createVisitInformation(VisitInformationDto visitInformationDto) {
        return new VisitInformation(
                LocalDate.of(EVENT_YEAR.getDate(), EVENT_MONTH.getDate(), visitInformationDto.visitDate()),
                new Orders(visitInformationDto.orders())
        );
    }

    public Admin createAdmin() {
        Admin admin = Admin.of();
        users.addAdmin(admin);

        return admin;
    }
}
