package dreamjob.store;

import dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище PostStore. Оно будет сингелтон.
 * Когда приложение запустится, то в хранилище будут три объекта Post.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class PostStore {
    private static final PostStore INST = new PostStore();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private static final AtomicInteger ID = new AtomicInteger(3);

    private PostStore() {
        posts.put(1, new Post(1,
                "Junior Java Job",
                "Less than 1 year experience",
                LocalDateTime.now()));
        posts.put(2, new Post(2,
                "Middle Java Job",
                "Experience from 1 to 3 years",
                LocalDateTime.now()));
        posts.put(3, new Post(3,
                "Senior Java Job",
                "Experience over 3 years",
                LocalDateTime.now()));
    }

    public static PostStore instOf() {
        return INST;
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
