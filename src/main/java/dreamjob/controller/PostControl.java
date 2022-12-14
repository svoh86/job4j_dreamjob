package dreamjob.controller;

import dreamjob.model.Post;
import dreamjob.model.User;
import dreamjob.service.CandidateService;
import dreamjob.service.CityService;
import dreamjob.service.PostService;
import dreamjob.utility.UserSession;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Контроллер.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Controller
public class PostControl {
    private final PostService postService;
    private final CityService cityService;

    public PostControl(PostService service, CityService cityService) {
        this.postService = service;
        this.cityService = cityService;
    }

    @GetMapping("/posts")
    public String posts(Model model, HttpSession session) {
        UserSession.getUserSession(model, session);
        model.addAttribute("posts", postService.findAll());
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model, HttpSession session) {
        UserSession.getUserSession(model, session);
        model.addAttribute("post",
                new Post(0, "Название вакансии", "Описание", null, false, null));
        model.addAttribute("cities", cityService.getAllCities());
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post, HttpSession session) {
        post.setCity(cityService.findById(post.getCity().getId()));
        post.setCreated(LocalDateTime.now());
        post.setUser((User) session.getAttribute("user"));
        postService.add(post);
        return "redirect:/myPosts";
    }

    @GetMapping("/formUpdatePost/{postID}")
    public String formUpdatePost(Model model, @PathVariable("postID") int id, HttpSession session) {
        UserSession.getUserSession(model, session);
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("cities", cityService.getAllCities());
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        post.setCity(cityService.findById(post.getCity().getId()));
        post.setCreated(LocalDateTime.now());
        postService.update(post);
        return "redirect:/myPosts";
    }

    @GetMapping("/deletePost/{postID}")
    public String deletePost(Model model, @PathVariable("postID") int id, HttpSession session) {
        UserSession.getUserSession(model, session);
        postService.delete(id);
        return "redirect:/myPosts";
    }
}
