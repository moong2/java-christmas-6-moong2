package christmas.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private static Users instance;
    private final List<User> users;

    private Users() {
        this.users = new ArrayList<>();
    }

    public static synchronized Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public User addUser(User user) {
        users.add(user);

        return user;
    }
}
