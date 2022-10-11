package dreamjob.service;

import dreamjob.model.City;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
@ThreadSafe
@Service
public class CityService {
    private final Map<Integer, City> cities = new HashMap<>();

    public CityService() {
        cities.put(1, new City(1, "Москва"));
        cities.put(2, new City(2, "Санкт-Петербург"));
        cities.put(3, new City(3, "Екатеринбург"));
        cities.put(4, new City(4, "Новосибирск"));
        cities.put(5, new City(5, "Сочи"));
        cities.put(6, new City(6, "Владивосток"));
        cities.put(7, new City(7, "Хабаровск"));
    }

    public List<City> getAllCities() {
        return new ArrayList<>(cities.values());
    }

    public City findById(int id) {
        return cities.get(id);
    }
}
