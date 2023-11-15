package christmas.model.domain;

import static christmas.util.exceptions.Exceptions.EVENT_FULL;

import java.util.ArrayList;
import java.util.List;

public class Admins {
    private final List<Admin> admins = new ArrayList<>();

    public void checkCanManage() {
        if (admins.size() == Integer.MAX_VALUE) {
            throw new IllegalStateException(EVENT_FULL.getMessage());
        }
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }
}
