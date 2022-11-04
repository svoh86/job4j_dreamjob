package dreamjob.service;

import dreamjob.model.Post;
import dreamjob.persistence.CityStore;
import dreamjob.persistence.PostDbStore;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Слой service
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Service
public class PostService {
    private final PostDbStore postDbStore;
    private final CityStore cityStore;

    public PostService(PostDbStore postDbStore, CityStore cityStore) {
        this.postDbStore = postDbStore;
        this.cityStore = cityStore;
    }

    public List<Post> findAll() {
        List<Post> posts = postDbStore.findAll();
        posts.forEach(
                p -> p.setCity(
                        cityStore.findById(p.getCity().getId())));
        return posts;
    }

    public void add(Post post) {
        postDbStore.add(post);
    }

    public Post findById(int id) {
        return postDbStore.findById(id);
    }

    public void update(Post post) {
        postDbStore.update(post);
    }

    public void delete(int id) {
        postDbStore.delete(id);
    }
}
