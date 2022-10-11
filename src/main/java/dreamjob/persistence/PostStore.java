package dreamjob.persistence;

import dreamjob.model.City;
import dreamjob.model.Post;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Слой persistence.
 * Хранилище PostStore.
 * Когда приложение запустится, то в хранилище будут три объекта Post.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Repository
public class PostStore {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private static final AtomicInteger ID = new AtomicInteger(3);

    public PostStore() {
        posts.put(1, new Post(1,
                "Junior Java Job",
                "Less than 1 year experience",
                LocalDateTime.now(), false, new City(0, "Самара")));
        posts.put(2, new Post(2,
                "Middle Java Job",
                "Experience from 1 to 3 years",
                LocalDateTime.now(), false, new City(0, "Самара")));
        posts.put(3, new Post(3,
                "Senior Java Job",
                "Experience over 3 years",
                LocalDateTime.now(), true, new City(0, "Самара")));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setId(ID.incrementAndGet());
        post.setCreated(LocalDateTime.now());
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        post.setCreated(LocalDateTime.now());
        posts.replace(post.getId(), post);
    }
}
