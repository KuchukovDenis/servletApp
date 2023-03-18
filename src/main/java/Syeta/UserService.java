package Syeta;


import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final UserSQLRepository repository = UserSQLRepository.getUserRepository();
    private final Map<String, People> sessionList = new HashMap<>();
    private static UserService service;

    private UserService() {
    }

    public static UserService getService() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    public void register(People user) {
        validateEmail(user.getEmail());
        repository.save(user);
    }

    private void validateEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!email.matches(regex)) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public boolean loginUser(People userCredentials, String session) {
        People user = repository.find(userCredentials.getLogin());
        if (user == null || !user.getPassword().equals(userCredentials.getPassword())) {
            return false;
        }
        sessionList.put(session, user);
        return true;
    }

    public void removeSession(String session) {
        sessionList.remove(session);
    }

    public People getUserFromCookie(String session) {
        return sessionList.get(session);
    }
}