package dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Controller
public class IndexControl {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
