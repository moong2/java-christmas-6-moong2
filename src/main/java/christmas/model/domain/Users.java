package christmas.model.domain;

/**
 * Clients와 Admins를 관리하는 싱글톤 객체이다. User를 추가하고 관리하는 메소드를 제공한다.
 */
public class Users {
    private static Users instance;
    private final Clients clients = new Clients();
    private final Admins admins = new Admins();

    /**
     * Users의 싱글톤 인스턴스를 받는 synchronized 메소드를 제공한다. 만약 인스턴스가 존재하지 않는다면, 새로 생성한다.
     *
     * @return Users의 싱글톤 인스턴스
     */
    public static synchronized Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    /**
     * Clients 컬렉션에 Client를 추가한다. 컬렉션에 추가하기 전에 Client가 이벤트에 참여할 수 있는지 확인한다.
     *
     * @param client 이벤트에 참여할 Client
     * @return 추가된 Client 객체
     */
    public Client addUser(Client client) {
        clients.checkCanParticipate();
        clients.addClient(client);

        return client;
    }

    /**
     * Admins 컬렉션에 Admin을 추가한다. 컬렉션에 추가하기 전에 Admin이 시스템에 관리자로 등록될 수 있는지 확인한다.
     *
     * @param admin 시스템에 등록될 Admin
     * @return 등록된 Admin 객체
     */
    public Admin addAdmin(Admin admin) {
        admins.checkCanManage();
        admins.addAdmin(admin);

        return admin;
    }

    /**
     * Clients 컬렉션을 반환한다.
     *
     * @return Client의 컬렉션을 보유하고 있는 Clients 객체
     */
    public Clients getClients() {
        return clients;
    }
}
