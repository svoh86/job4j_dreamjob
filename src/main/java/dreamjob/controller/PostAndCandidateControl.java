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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * Контроллер.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Controller
public class PostAndCandidateControl {
    private final PostService postService;
    private final CandidateService candidateService;

    public PostAndCandidateControl(PostService service, CandidateService candidateService) {
        this.postService = service;
        this.candidateService = candidateService;
    }

    @GetMapping("/myPosts")
    public String myPosts(Model model, HttpSession session) {
        UserSession.getUserSession(model, session);
        User user = (User) session.getAttribute("user");
        model.addAttribute("posts", postService.findByUserId(user.getId()));
        model.addAttribute("candidates", candidateService.findByUserId(user.getId()));
        return "myPosts";
    }
}
