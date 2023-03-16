package Syeta;

import java.util.HashMap;
import java.util.Map;

public class UserRepository  {
    private static UserRepository repository;
    private final Map<String, People> users = new HashMap<>();


    public void save(People user) {
        users.put(user.getLogin(), user);
    }


    public People find(String login) {
        return users.get(login);
    }

    private UserRepository() {
    }

    public static UserRepository getUserRepository() {
        if (repository == null) {
            repository = new UserRepository();
        }

        return repository;
    }
}