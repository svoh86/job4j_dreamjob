package dreamjob.service;

import dreamjob.model.Post;
import dreamjob.persistence.PostStore;

import java.util.Collection;

/**
 * Слой service. Singleton
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class PostService {
    private static final PostService INST = new PostService();
    private final PostStore store = PostStore.instOf();

    private PostService() {
    }

    public static PostService instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return store.findAll();
    }

    public void add(Post post) {
        store.add(post);
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public void update(Post post) {
        store.update(post);
    }
}
