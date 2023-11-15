package christmas.model.calculator;

import christmas.model.domain.Client;
import java.util.List;

public class StatisticCalculator {

    private static final int ANTICIPATE_PERCENT = 5;
    private static final double PERCENT = 100.0;
    private static final double ROUND = 100.0;

    public static int numberOfClients(List<Client> clients) {
        return clients.size();
    }

    public static double nextEventNumberOfClients(List<Client> clients) {
        int numberOfClients = numberOfClients(clients);
        double nextEventNumberOfClients = numberOfClients * (ANTICIPATE_PERCENT / PERCENT);

        return Math.round(nextEventNumberOfClients * ROUND) / ROUND;
    }

    public static long totalSalesAmount(List<Client> clients) {
        long totalSalesAmount = 0L;

        for (Client client : clients) {
            totalSalesAmount += client.getTotalAmountBeforeDiscount();
        }

        return totalSalesAmount;
    }
}
