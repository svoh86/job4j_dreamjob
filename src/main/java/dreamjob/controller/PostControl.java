package dreamjob.controller;

import dreamjob.model.Post;
import dreamjob.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Контроллер.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Controller
public class PostControl {
    private final PostService service;

    public PostControl(PostService service) {
        this.service = service;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", service.findAll());
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model) {
        model.addAttribute("post",
                new Post(0, "Название вакансии", "Описание", null));
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        service.add(post);
        return "redirect:/posts";
    }

    @GetMapping("/formUpdatePost/{postID}")
    public String formUpdatePost(Model model, @PathVariable("postID") int id) {
        model.addAttribute("post", service.findById(id));
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        service.update(post);
        return "redirect:/posts";
    }
}
