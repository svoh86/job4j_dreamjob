package dreamjob.service;

import dreamjob.model.Candidate;
import dreamjob.model.Post;
import dreamjob.persistence.CandidateDbStore;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Слой service
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Service
public class CandidateService {
    private final CandidateDbStore store;

    public CandidateService(CandidateDbStore store) {
        this.store = store;
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public void add(Candidate candidate) {
        store.add(candidate);
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }

    public void updateWithoutPhoto(Candidate candidate) {
        store.updateWithoutPhoto(candidate);
    }

    public void delete(int id) {
        store.delete(id);
    }

    public List<Candidate> findByUserId(int id) {
        return store.findByUserId(id);
    }
}
