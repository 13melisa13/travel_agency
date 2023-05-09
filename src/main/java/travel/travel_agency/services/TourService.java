package travel.travel_agency.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travel.travel_agency.entities.*;
import travel.travel_agency.repositories.CityRepository;
import travel.travel_agency.repositories.TourRepository;

import java.util.Date;
import java.util.List;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TourService {
    private final TourRepository repository;
    public void saveNewTour(Tour tour){
        repository.save(tour);
        log.info("New tour was saved {}",tour);
    }
    public void deleteAll(){
        repository.deleteAll();
        log.info("All tours were deleted");
    }

    public  List<Tour> findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSeaAndPriceLessThan
            (Date dateFrom, Date dateTo, City city, boolean presentSea,Integer price){
        log.info("All tours were found");
        return repository.findByDateFromAfterAndDateToBeforeAndCityContainsAndIsPresentSeaAndPriceLessThan
                (dateFrom, dateTo, city, presentSea, price);
    }

    public List<Tour> findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSea
            (Date dateFrom, Date dateTo, City city, boolean presentSea){
        log.info("All tours were found");
        return repository.findByDateFromAfterAndDateToBeforeAndCityContainsAndIsPresentSea
                (dateFrom, dateTo, city, presentSea);
    }
    public  List<Tour> findByDateFromAfterAndDateToBeforeAndCityContains
            (Date dateFrom, Date dateTo, City city){
        log.info("All tours were found");
        return repository.findByDateFromAfterAndDateToBeforeAndCityContains(dateFrom, dateTo, city);
    }
    public  List<Tour> findByDateFromAfterAndDateToBefore
            (Date dateFrom, Date dateTo){
        log.info("All tours were found");
        return repository.findByDateFromAfterAndDateToBefore(dateFrom, dateTo);
    }
    public List<Tour> findAll(){
        log.info("All countries were found");
        return (List<Tour>) repository.findAll();
    }
    public List<Tour> findAllByBought(User user){
        log.info("All countries were found");
        return (List<Tour>) repository.findAllByBoughtBy(user);
    }

}
