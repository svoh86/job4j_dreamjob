package dreamjob.persistence;

import dreamjob.Main;
import dreamjob.model.Post;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PostDbStoreTest {

    @Test
    public void whenCreatePost() {
        PostDbStore store = new PostDbStore(new Main().loadPool());
        Post post = new Post(0, "Java Job");
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName()).isEqualTo(post.getName());
    }
}