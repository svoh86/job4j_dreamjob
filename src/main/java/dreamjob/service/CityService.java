package dreamjob.service;

import dreamjob.model.City;
import dreamjob.persistence.CityStore;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Слой service
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Service
public class CityService {
    private final CityStore cityStore;

    public CityService(CityStore cityStore) {
        this.cityStore = cityStore;
    }

    public List<City> getAllCities() {
        return cityStore.getAllCities();
    }

    public City findById(int id) {
        return cityStore.findById(id);
    }
}
