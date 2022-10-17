package dreamjob.controller;

import dreamjob.model.User;
import dreamjob.service.CityService;
import dreamjob.service.PostService;
import dreamjob.service.UserService;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * Контроллер.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Controller
public class UserControl {
    private final UserService userService;

    public UserControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/formAddUser")
    public String addUser(Model model) {
        model.addAttribute("post",
                new User(0, "email", "password"));
        return "addUser";
    }

    @PostMapping("/createUser")
    public String createUser(Model model, @ModelAttribute User user) {
        Optional<User> regUser = userService.add(user);
        if (regUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с такой почтой уже существует");
            return "redirect:/failRegistration";
        }
        return "redirect:/successRegistration";
    }

    @GetMapping("/failRegistration")
    public String failRegistration(Model model) {
        model.addAttribute("post",
                new User(0, "email", "password"));
        return "failRegistration";
    }

    @GetMapping("/successRegistration")
    public String successRegistration(Model model) {
        model.addAttribute("post",
                new User(0, "email", "password"));
        return "successRegistration";
    }
}