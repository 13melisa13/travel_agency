package travel.travel_agency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.travel_agency.entities.Tour;
import travel.travel_agency.entities.User;

import java.util.Optional;
@Repository
public interface TourRepository extends JpaRepository<Tour,Integer> {
}
