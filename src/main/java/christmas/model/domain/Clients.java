package christmas.model.domain;

import static christmas.util.exceptions.Exceptions.EVENT_FULL;

import java.util.ArrayList;
import java.util.List;

public class Clients {
    private final List<Client> clients = new ArrayList<>();

    public List<Client> getClients() {
        return clients;
    }

    public void checkCanParticipate() {
        if (clients.size() == Integer.MAX_VALUE) {
            throw new IllegalStateException(EVENT_FULL.getMessage());
        }
    }

    public void addClient(Client client) {
        clients.add(client);
    }
}
