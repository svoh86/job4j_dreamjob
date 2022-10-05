package dreamjob.controller;

import dreamjob.model.Candidate;
import dreamjob.store.CandidateStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@Controller
public class CandidateController {
    private final CandidateStore store = CandidateStore.instOf();

    @GetMapping("/candidates")
    public String candidates(Model model) {
        model.addAttribute("candidates", store.findAll());
        return "candidates";
    }

    @GetMapping("/formAddCandidate")
    public String addCandidate(Model model) {
        model.addAttribute("candidate",
                new Candidate(0, "Имя", "Описание", null));
        return "addCandidate";
    }
}
