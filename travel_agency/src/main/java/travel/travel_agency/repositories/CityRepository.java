package travel.travel_agency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.travel_agency.entities.City;
import travel.travel_agency.entities.Tour;
@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
}
