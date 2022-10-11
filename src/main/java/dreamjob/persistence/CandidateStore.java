package dreamjob.persistence;

import dreamjob.model.Candidate;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Слой persistence.
 * Аналогично PostStore
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Repository
public class CandidateStore {
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private static final AtomicInteger ID = new AtomicInteger(3);

    public CandidateStore() {
        candidates.put(1, new Candidate(1,
                "Junior Java Dev",
                "Experience near 1 year",
                LocalDateTime.now()));
        candidates.put(2, new Candidate(2,
                "Middle Java Dev",
                "Experience 3 years",
                LocalDateTime.now()));
        candidates.put(3, new Candidate(3,
                "Senior Java Dev",
                "Experience over 3 years",
                LocalDateTime.now()));
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidate.setId(ID.incrementAndGet());
        candidate.setCreated(LocalDateTime.now());
        candidates.put(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate candidate) {
        candidate.setCreated(LocalDateTime.now());
        candidates.replace(candidate.getId(), candidate);
    }
}
