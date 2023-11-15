package christmas.model.domain;

import christmas.model.calculator.StatisticCalculator;
import christmas.model.util.user.UserRoles;

public class Admin {
    private final Role role;

    private Admin() {
        role = new Role(UserRoles.ADMIN);
    }

    public static Admin of() {
        return new Admin();
    }

    public int analyzeTotalParticipants(Clients clients) {
        UserRoles.authenticateAdmin(role.userRole());

        return StatisticCalculator.numberOfClients(clients.getClients());
    }

    public double analyzeNextEventParticipants(Clients clients) {
        UserRoles.authenticateAdmin(role.userRole());

        return StatisticCalculator.nextEventNumberOfClients(clients.getClients());
    }

    public long analyzeTotalEventOrders(Clients clients) {
        UserRoles.authenticateAdmin(role.userRole());

        return StatisticCalculator.totalSalesAmount(clients.getClients());
    }
}
