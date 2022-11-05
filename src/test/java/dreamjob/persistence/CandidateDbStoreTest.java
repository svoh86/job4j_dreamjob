package dreamjob.persistence;

import dreamjob.Main;
import dreamjob.model.Candidate;
import dreamjob.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class CandidateDbStoreTest {

    @Test
    public void whenCreateCandidate() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate = new Candidate(1, "Ivan", "Java Dev", LocalDateTime.now(), null);
        candidate.setUser(new User());
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName()).isEqualTo(candidate.getName());
    }

    @Test
    public void whenUpdateCandidate() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate = new Candidate(1, "Ivan", "Java Dev", LocalDateTime.now(), null);
        candidate.setUser(new User());
        store.add(candidate);
        store.update(new Candidate(1, "Petr", "Java Dev", LocalDateTime.now(), null));
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName()).isEqualTo(candidate.getName());
    }

    @Test
    public void whenFindByIdCandidate() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate = new Candidate(10, "Ivan", "Java Dev", LocalDateTime.now(), null);
        candidate.setUser(new User());
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getId()).isEqualTo(candidate.getId());
    }
}