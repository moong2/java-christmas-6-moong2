package christmas.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Clients {
    private static Clients instance;
    private final List<Client> clients;

    private Clients() {
        this.clients = new ArrayList<>();
    }

    public static synchronized Clients getInstance() {
        if (instance == null) {
            instance = new Clients();
        }
        return instance;
    }

    public Client addUser(Client client) {
        clients.add(client);

        return client;
    }
}
