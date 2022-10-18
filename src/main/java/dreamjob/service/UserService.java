package dreamjob.service;

import dreamjob.model.User;
import dreamjob.persistence.UserDbStore;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Слой service
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Service
public class UserService {
    private final UserDbStore userDbStore;

    public UserService(UserDbStore userDbStore) {
        this.userDbStore = userDbStore;
    }

    public Optional<User> add(User user) {
        return userDbStore.add(user);
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return userDbStore.findUserByEmailAndPassword(email, password);
    }
}
