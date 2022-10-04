package dreamjob.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@RestController
public class IndexControl {
    @GetMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!!!";
    }
}
