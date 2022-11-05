package dreamjob.controller;

import dreamjob.model.Candidate;
import dreamjob.model.User;
import dreamjob.service.CandidateService;
import dreamjob.utility.UserSession;
import net.jcip.annotations.ThreadSafe;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Controller
public class CandidateControl {
    private final CandidateService candidateService;

    public CandidateControl(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/candidates")
    public String candidates(Model model, HttpSession session) {
        UserSession.getUserSession(model, session);
        model.addAttribute("candidates", candidateService.findAll());
        return "candidates";
    }

    @GetMapping("/formAddCandidate")
    public String addCandidate(Model model, HttpSession session) {
        UserSession.getUserSession(model, session);
        model.addAttribute("candidate",
                new Candidate(0, "Имя", "Описание", null, new byte[0]));
        return "addCandidate";
    }

    @PostMapping("/createCandidate")
    public String createCandidate(@ModelAttribute Candidate candidate,
                                  @RequestParam("file") MultipartFile file,
                                  HttpSession session) throws IOException {
        candidate.setCreated(LocalDateTime.now());
        candidate.setPhoto(file.getBytes());
        candidate.setUser((User) session.getAttribute("user"));
        candidateService.add(candidate);
        return "redirect:/myPosts";
    }

    @GetMapping("/formUpdateCandidate/{candidateID}")
    public String formUpdateCandidate(Model model, @PathVariable("candidateID") int id, HttpSession session) {
        UserSession.getUserSession(model, session);
        model.addAttribute("candidate", candidateService.findById(id));
        return "updateCandidate";
    }

    @PostMapping("/updateCandidate")
    public String updateCandidate(@ModelAttribute Candidate candidate,
                                  @RequestParam("file") MultipartFile file) throws IOException {
        candidate.setCreated(LocalDateTime.now());
        candidate.setPhoto(file.getBytes());
        if (candidate.getPhoto().length != 0) {
            candidateService.update(candidate);
        } else {
            candidateService.updateWithoutPhoto(candidate);
        }
        return "redirect:/myPosts";
    }

    @GetMapping("/photoCandidate/{candidateId}")
    public ResponseEntity<Resource> download(@PathVariable("candidateId") Integer candidateId) {
        Candidate candidate = candidateService.findById(candidateId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(candidate.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(candidate.getPhoto()));
    }

    @GetMapping("/deleteCandidate/{candidateID}")
    public String deleteCandidate(Model model, @PathVariable("candidateID") int id, HttpSession session) {
        UserSession.getUserSession(model, session);
        candidateService.delete(id);
        return "redirect:/myPosts";
    }
}
