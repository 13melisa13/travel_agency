package travel.travel_agency.controllers;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import travel.travel_agency.entities.*;
import travel.travel_agency.services.CityService;
import travel.travel_agency.services.CountryService;
import travel.travel_agency.services.TourService;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;
    private final CityService cityService;
    private final CountryService countryService;
    @GetMapping("/tour/all")
    public List<Tour> findAll() {
        return tourService.findAll();
    }
    @DeleteMapping("/tour/all")
    public void deleteAll() {
        tourService.deleteAll();
    }
    @GetMapping("/tour/oneByBought")
    public List<Tour> findAllByBought(@ModelAttribute User user) {
        return tourService.findAllByBought(user);
    }
    @ModelAttribute
    @GetMapping("/new_tour_form")
    public ModelAndView newTourFormGet(Model model){
        Tour tour = new Tour();
        model.addAttribute("tour", tour);
        return new ModelAndView("/new_tour_form");
    }
    @PostConstruct
    public void addTours(){

        Tour tour = new Tour(true, TypeOfBeach.Sand , 1450, new Date(), new Date(), new City("nameOfCity", new Country("nameOfCountry")));
        countryService.saveNewCountry(tour.getCity().getCountry());
        cityService.saveNewCity(tour.getCity());
        tourService.saveNewTour(tour);
    }
    @PostMapping("/new_tour_form")
    public ModelAndView newTourFormPost(@ModelAttribute("tour") Tour tour,
                                        Model model){
        try {
            countryService.saveNewCountry(tour.getCity().getCountry());
            cityService.saveNewCity(tour.getCity());

            tourService.saveNewTour(tour);
            return new ModelAndView(new RedirectView("/admin"));
        } catch (Exception e) {
            return new ModelAndView("/new_tour_form");
        }

    }
    @GetMapping("/tour/oneByPrice")
    public  List<Tour> findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSeaAndPriceLessThan
            (Date dateFrom, Date dateTo, City city, boolean presentSea, Integer price){
        return tourService.findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSeaAndPriceLessThan
                (dateFrom, dateTo, city, presentSea, price);
    }
    @GetMapping("/tour/oneBySea")
    public List<Tour> findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSea
            (@ModelAttribute Date dateFrom,@ModelAttribute Date dateTo,@ModelAttribute City city,@ModelAttribute boolean presentSea){
        return tourService.findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSea
                (dateFrom, dateTo, city, presentSea);
    }
    @GetMapping("/tour/oneByCity")
    public  List<Tour> findByDateFromAfterAndDateToBeforeAndCityContains
            (@ModelAttribute Date dateFrom,@ModelAttribute Date dateTo,@ModelAttribute City city){
        return tourService.findByDateFromAfterAndDateToBeforeAndCityContains(dateFrom, dateTo, city);
    }
    @GetMapping("/tour/oneByDate")
    public  List<Tour> findByDateFromAfterAndDateToBefore
            (@ModelAttribute Date dateFrom,@ModelAttribute Date dateTo){
        return tourService.findByDateFromAfterAndDateToBefore(dateFrom, dateTo);
    }
}
