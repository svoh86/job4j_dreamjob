package dreamjob.persistence;

import dreamjob.model.City;
import dreamjob.model.Post;
import org.apache.commons.dbcp2.BasicDataSource;
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

    public PostDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select * from post")
        ) {
            try (ResultSet it = statement.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getTimestamp("created").toLocalDateTime(),
                            it.getBoolean("visible"),
                            new City(it.getInt("city_id"), "Город")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public Post add(Post post) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "insert into post(name, description, created, visible, city_id) values (?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
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
            e.printStackTrace();
        }
        return post;
    }

    public void update(Post post) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "update post set name = ?, description = ?, created = ?, visible = ?, city_id = ? where id = ?")
        ) {
            statement.setString(1, post.getName());
            statement.setString(2, post.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(post.getCreated()));
            statement.setBoolean(4, post.isVisible());
            statement.setInt(5, post.getCity().getId());
            statement.setInt(6, post.getId());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Post findById(int id) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("Select * from post where id = ?")
        ) {
            statement.setInt(1, id);
            try (ResultSet it = statement.executeQuery()) {
                if (it.next()) {
                    return new Post(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getTimestamp("created").toLocalDateTime(),
                            it.getBoolean("visible"),
                            new City(it.getInt("city_id"), null)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
