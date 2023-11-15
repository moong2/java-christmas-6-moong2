package christmas.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private static Users instance;
    private final List<Client> clients;

    private Users() {
        this.clients = new ArrayList<>();
    }

    public static synchronized Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public Client addUser(Client client) {
        clients.add(client);

        return client;
    }
}
