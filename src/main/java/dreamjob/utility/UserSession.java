package dreamjob.utility;

import dreamjob.model.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public final class UserSession {
    private UserSession() {
    }

    public static void getUserSession(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
    }
}
