package dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Controller
public class IndexControl {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
