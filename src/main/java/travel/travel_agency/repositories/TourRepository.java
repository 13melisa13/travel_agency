package travel.travel_agency.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import travel.travel_agency.entities.City;
import travel.travel_agency.entities.Tour;
import travel.travel_agency.entities.User;

import java.util.Date;
import java.util.List;

@Repository
public interface TourRepository extends CrudRepository<Tour,Integer> {
    Tour findTourById(Integer id);
    List<Tour> findAllByBoughtBy(User user);
    List<Tour> findByDateFromAfterAndDateToBefore(Date dateFrom, Date dateTo);
    List<Tour> findByDateFromAfterAndDateToBeforeAndCityContains(Date dateFrom, Date dateTo, City city);
    List<Tour> findByDateFromAfterAndDateToBeforeAndCityContainsAndPriceLessThan(Date dateFrom, Date dateTo, City city, Integer price);


}
