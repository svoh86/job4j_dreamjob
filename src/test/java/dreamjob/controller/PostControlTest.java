package dreamjob.controller;

import dreamjob.model.City;
import dreamjob.model.Post;
import dreamjob.service.CityService;
import dreamjob.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostControlTest {

    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post"),
                new Post(2, "New post")
        );
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        when(postService.findAll()).thenReturn(posts);
        PostControl postControl = new PostControl(postService, cityService);
        String page = postControl.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page).isEqualTo("posts");
    }

    @Test
    public void whenCreatePost() {
        Post post = new Post(1,
                "New post",
                "desc",
                LocalDateTime.now(),
                false,
                new City());
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(postService, cityService);
        String page = postController.createPost(post);
        verify(postService).add(post);
        assertThat(page).isEqualTo("redirect:/posts");
    }

    @Test
    public void whenAddPost() {
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(postService, cityService);
        String page = postController.addPost(model, session);
        assertThat(page).isEqualTo("addPost");
    }

    @Test
    public void whenFormUpdatePost() {
        Post post = new Post(1,
                "New post",
                "desc",
                LocalDateTime.now(),
                false,
                new City());
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        when(postService.findById(anyInt())).thenReturn(post);
        PostControl postController = new PostControl(postService, cityService);
        String page = postController.formUpdatePost(model, 1, session);
        verify(model).addAttribute("post", post);
        assertThat(page).isEqualTo("updatePost");
    }

    @Test
    public void whenUpdatePost() {
        Post post = new Post(1,
                "New post",
                "desc",
                LocalDateTime.now(),
                false,
                new City());
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(postService, cityService);
        String page = postController.updatePost(post);
        verify(postService).update(post);
        assertThat(page).isEqualTo("redirect:/posts");
    }
}