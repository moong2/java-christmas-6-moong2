package christmas.model.domain;

public class Users {
    private static Users instance;
    private final Clients clients = new Clients();
    private final Admins admins = new Admins();

    public static synchronized Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public Client addUser(Client client) {
        clients.checkCanParticipate();
        clients.addClient(client);

        return client;
    }

    public Admin addAdmin(Admin admin) {
        admins.checkCanManage();
        admins.addAdmin(admin);

        return admin;
    }

    public Clients getClients() {
        return clients;
    }
}
