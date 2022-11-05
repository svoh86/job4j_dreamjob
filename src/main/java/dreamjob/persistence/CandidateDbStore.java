package dreamjob.persistence;

import dreamjob.model.Candidate;
import dreamjob.model.Post;
import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Слой persistence.
 * Аналогично PostStore
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Repository
public class CandidateDbStore {
    private final BasicDataSource pool;
    private final static Logger LOG = LogManager.getLogger(CandidateDbStore.class);
    private final static String FIND_ALL = "SELECT * FROM candidate";
    private final static String ADD = "INSERT INTO candidate (name, description, created, photo, user_id)"
                                      + " VALUES (?, ?, ?, ?, ?)";
    private final static String FIND_BY_ID = "SELECT * FROM candidate WHERE id = ?";
    private final static String UPDATE = "UPDATE candidate SET name = ?, description = ?, "
                                         + "created = ?, photo = ? WHERE id = ?";
    private final static String UPDATE_WITHOUT_PHOTO = "UPDATE candidate SET name = ?, description = ?, "
                                                       + "created = ? WHERE id = ?";
    private final static String DELETE = "DELETE FROM candidate WHERE id = ?";

    private final static String FIND_BY_USER_ID = "SELECT * FROM candidate WHERE user_id = ?";
    private static final Comparator<Candidate> COMPARE_BY_ID = Comparator.comparingInt(Candidate::getId);

    public CandidateDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)
        ) {
            try (ResultSet it = statement.executeQuery()) {
                while (it.next()) {
                    candidates.add(getCandidate(it));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in method findAll()", e);
        }
        candidates.sort(COMPARE_BY_ID);
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD,
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(candidate.getCreated()));
            statement.setBytes(4, candidate.getPhoto());
            statement.setInt(5, candidate.getUser().getId());
            statement.execute();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in method add(Candidate candidate)", e);
        }
        return candidate;
    }

    public Candidate findById(int id) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)
        ) {
            statement.setInt(1, id);
            try (ResultSet it = statement.executeQuery()) {
                if (it.next()) {
                    return getCandidate(it);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in method findById(int id)", e);
        }
        return null;
    }

    public void update(Candidate candidate) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)
        ) {
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(candidate.getCreated()));
            statement.setBytes(4, candidate.getPhoto());
            statement.setInt(5, candidate.getId());
            statement.execute();
        } catch (Exception e) {
            LOG.error("Exception in method update(Candidate candidate)", e);
        }
    }

    public void updateWithoutPhoto(Candidate candidate) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_WITHOUT_PHOTO)
        ) {
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(candidate.getCreated()));
            statement.setInt(4, candidate.getId());
            statement.execute();
        } catch (Exception e) {
            LOG.error("Exception in method update(Candidate candidate)", e);
        }
    }

    public void delete(int id) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            LOG.error("Exception in method delete()", e);
        }
    }

    public List<Candidate> findByUserId(int id) {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)
        ) {
            statement.setInt(1, id);
            try (ResultSet it = statement.executeQuery()) {
                while (it.next()) {
                    candidates.add(getCandidate(it));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in method findByUserId()", e);
        }
        return candidates;
    }

    private Candidate getCandidate(ResultSet it) throws SQLException {
        return new Candidate(
                it.getInt("id"),
                it.getString("name"),
                it.getString("description"),
                it.getTimestamp("created").toLocalDateTime(),
                it.getBytes("photo")
        );
    }
}
