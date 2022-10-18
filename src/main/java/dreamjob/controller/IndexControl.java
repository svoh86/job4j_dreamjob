package dreamjob.controller;

import dreamjob.utility.UserSession;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Controller
public class IndexControl {
    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        UserSession.getUserSession(model, session);
        return "index";
    }
}
