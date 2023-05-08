package travel.travel_agency.controllers;
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
        Country country = new Country();
        City city = new City();
        model.addAttribute("country", country);
        //countryService.saveNewCountry(country);
        model.addAttribute("city", city);
        //cityService.saveNewCity(city);
        Tour tour = new Tour();
        model.addAttribute("tour", tour);
        tour.setCity(city);
        return new ModelAndView("/new_tour_form");
    }
    @PostMapping("/new_tour_form")
    public ModelAndView newTourFormPost(@ModelAttribute("tour") Tour tour,
                                        @ModelAttribute("country") Country country,
                                        @ModelAttribute("city") City city,
                                        Model model){
        try {
            countryService.saveNewCountry(country);
            cityService.saveNewCity(city);
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
