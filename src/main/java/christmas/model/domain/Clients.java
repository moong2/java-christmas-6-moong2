package christmas.model.domain;

import static christmas.util.exceptions.Exceptions.EVENT_FULL;

import java.util.ArrayList;
import java.util.List;

/**
 * Client 객체의 컬렉션을 나타낸다. Client 객체를 관리하고 유지하는 기능을 제공한다.
 */
public class Clients {
    private final List<Client> clients = new ArrayList<>();

    /**
     * Client List를 반환한다.
     *
     * @return Client 리스트
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     * Client가 이벤트에 참가할 수 있는지 확인한다. 만약 Client가 이벤트에 참가할 수 없다면 예외를 던진다. 이벤트에 참가할 수 있는 Client의 수는 Integer의 최댓값이다.
     *
     * @throws IllegalStateException Client의 수가 Integer.MAX_VALUE에 도달한 경우
     */
    public void checkCanParticipate() {
        if (clients.size() == Integer.MAX_VALUE) {
            throw new IllegalStateException(EVENT_FULL.getMessage());
        }
    }

    /**
     * Client를 컬렉션에 추가한다.
     *
     * @param client 추가할 Client 객체
     */
    public void addClient(Client client) {
        clients.add(client);
    }
}
