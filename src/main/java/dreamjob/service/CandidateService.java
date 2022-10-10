package dreamjob.service;

import dreamjob.model.Candidate;
import dreamjob.persistence.CandidateStore;

import java.util.Collection;

/**
 * Слой service. Singleton
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class CandidateService {
    private static final CandidateService INST = new CandidateService();
    private final CandidateStore store = CandidateStore.instOf();

    private CandidateService() {
    }

    public static CandidateService instOf() {
        return INST;
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
}
