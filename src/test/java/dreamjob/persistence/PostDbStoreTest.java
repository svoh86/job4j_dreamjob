package dreamjob.persistence;

import dreamjob.Main;
import dreamjob.model.City;
import dreamjob.model.Post;
import dreamjob.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class PostDbStoreTest {

    @Test
    public void whenCreatePost() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Dev", LocalDateTime.now(), true, new City());
        post.setUser(new User());
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName()).isEqualTo(post.getName());
    }

    @Test
    public void whenUpdatePost() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Dev", LocalDateTime.now(), true, new City());
        post.setUser(new User());
        store.add(post);
        store.update(new Post(1, "Junior Job", "Dev", LocalDateTime.now(), false, new City()));
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName()).isEqualTo(post.getName());
    }

    @Test
    public void whenFindByIdPost() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Dev", LocalDateTime.now(), true, new City());
        post.setUser(new User());
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getId()).isEqualTo(post.getId());
    }
}