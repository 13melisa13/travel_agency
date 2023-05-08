package travel.travel_agency.controllers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import travel.travel_agency.entities.City;
import travel.travel_agency.entities.Tour;
import travel.travel_agency.entities.User;
import travel.travel_agency.services.TourService;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TourController {
    private final TourService service;
    @GetMapping("/tour/all")
    public List<Tour> findAll() {
        return service.findAll();
    }
    @DeleteMapping("/tour/all")
    public void deleteAll() {
        service.deleteAll();
    }
    @GetMapping("/tour/oneByBought")
    public List<Tour> findAllByBought(@RequestParam User user) {
        return service.findAllByBought(user);
    }
    @PostMapping(path= "/tour/one")
    public void save(@RequestParam Tour tour){
        service.saveNewTour(tour);
    }
    @GetMapping("/tour/oneByPrice")
    public  List<Tour> findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSeaAndPriceLessThan
            (Date dateFrom, Date dateTo, City city, boolean presentSea, Integer price){
        return service.findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSeaAndPriceLessThan
                (dateFrom, dateTo, city, presentSea, price);
    }
    @GetMapping("/tour/oneBySea")
    public List<Tour> findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSea
            (Date dateFrom, Date dateTo, City city, boolean presentSea){
        return service.findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSea
                (dateFrom, dateTo, city, presentSea);
    }
    @GetMapping("/tour/oneByCity")
    public  List<Tour> findByDateFromAfterAndDateToBeforeAndCityContains
            (Date dateFrom, Date dateTo, City city){
        return service.findByDateFromAfterAndDateToBeforeAndCityContains(dateFrom, dateTo, city);
    }
    @GetMapping("/tour/oneByDate")
    public  List<Tour> findByDateFromAfterAndDateToBefore
            (Date dateFrom, Date dateTo){
        return service.findByDateFromAfterAndDateToBefore(dateFrom, dateTo);
    }
}
