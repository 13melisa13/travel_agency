package travel.travel_agency.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import travel.travel_agency.entities.City;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City,Integer> {
    List<City> findAllByOrderByNameAsc();
    City findByName(String name);
}
