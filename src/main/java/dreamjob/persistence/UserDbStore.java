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
}
