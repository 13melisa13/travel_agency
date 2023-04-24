package travel.travel_agency.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import travel.travel_agency.entities.Country;
import travel.travel_agency.entities.Tour;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country,Integer> {
   List<Country> findAllBy(Sort sort);
   Country findByName(String name);
}
