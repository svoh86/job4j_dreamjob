package dreamjob.store;

import dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Аналогично PostStore
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
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

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
