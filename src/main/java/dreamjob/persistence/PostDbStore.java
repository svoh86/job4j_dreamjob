package dreamjob.persistence;

import dreamjob.model.City;
import dreamjob.model.Post;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Repository
public class PostDbStore {
    private final BasicDataSource pool;
    private static final Logger LOG = LogManager.getLogger(PostDbStore.class);
    private final static String FIND_ALL = "SELECT * FROM post";
    private final static String ADD = "INSERT INTO post(name, description, created, visible, city_id)"
                                      + " VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE post SET name = ?, description = ?, created = ?,"
                                         + " visible = ?, city_id = ? WHERE id = ?";
    private final static String FIND_BY_ID = "SELECT * FROM post WHERE id = ?";

    public PostDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)
        ) {
            try (ResultSet it = statement.executeQuery()) {
                while (it.next()) {
                    posts.add(getPost(it));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in method findAll()", e);
        }
        return posts;
    }

    public Post add(Post post) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     ADD, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, post.getName());
            statement.setString(2, post.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(post.getCreated()));
            statement.setBoolean(4, post.isVisible());
            statement.setInt(5, post.getCity().getId());
            statement.execute();
            try (ResultSet id = statement.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in method add()", e);
        }
        return post;
    }

    public void update(Post post) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)
        ) {
            statement.setString(1, post.getName());
            statement.setString(2, post.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(post.getCreated()));
            statement.setBoolean(4, post.isVisible());
            statement.setInt(5, post.getCity().getId());
            statement.setInt(6, post.getId());
            statement.execute();
        } catch (Exception e) {
            LOG.error("Exception in method update()", e);
        }
    }

    public Post findById(int id) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)
        ) {
            statement.setInt(1, id);
            try (ResultSet it = statement.executeQuery()) {
                if (it.next()) {
                    return getPost(it);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in method findById()", e);
        }
        return null;
    }

    private Post getPost(ResultSet it) throws SQLException {
        return new Post(
                it.getInt("id"),
                it.getString("name"),
                it.getString("description"),
                it.getTimestamp("created").toLocalDateTime(),
                it.getBoolean("visible"),
                new City(it.getInt("city_id"), "Город")
        );
    }
}
