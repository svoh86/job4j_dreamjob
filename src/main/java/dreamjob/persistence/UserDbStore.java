package dreamjob.persistence;

import dreamjob.model.User;
import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Repository
public class UserDbStore {
    private final BasicDataSource pool;
    private static final Logger LOG = LogManager.getLogger(UserDbStore.class);
    private final static String ADD = "INSERT INTO users(email, password) VALUES (?, ?)";
    private final static String FIND_USER_BY_EMAIL_AND_PWD = "SELECT * FROM users WHERE email = ? AND password = ?";

    public UserDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<User> add(User user) {
        Optional<User> rsl = Optional.empty();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     ADD, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.execute();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                    rsl = Optional.of(user);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in method add()", e);
        }
        return rsl;
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        Optional<User> rsl = Optional.empty();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PWD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet it = statement.executeQuery()) {
                if (it.next()) {
                    rsl = Optional.of(new User(
                            it.getInt("id"),
                            it.getString("email"),
                            it.getString("password")
                    ));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in method findUserByEmailAndPwd()", e);
        }
        return rsl;
    }
}
