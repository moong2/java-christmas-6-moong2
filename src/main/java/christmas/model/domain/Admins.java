package christmas.model.domain;

import static christmas.util.exceptions.Exceptions.EVENT_FULL;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin 객체의 컬렉션을 나타낸다. Admin 객체들을 관리하고 유지하는 기능을 제공한다.
 */
public class Admins {
    private final List<Admin> admins = new ArrayList<>();

    /**
     * 시스템에 Admin이 추가될 수 있는지 확인한다. Admin이 포화 상태라면 예외를 던진다. 최대 Admin의 수는 integer의 최댓값이다.
     *
     * @throws IllegalStateException Admin의 수가 Integer.MAX_VALUE에 도달하였을 경우
     */
    public void checkCanManage() {
        if (admins.size() == Integer.MAX_VALUE) {
            throw new IllegalStateException(EVENT_FULL.getMessage());
        }
    }

    /**
     * 시스템에 Admin을 추가한다.
     *
     * @param admin 추가할 Admin 객체
     */
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }
}
