package dreamjob.service;

import dreamjob.model.Candidate;
import dreamjob.persistence.CandidateDbStore;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
}
