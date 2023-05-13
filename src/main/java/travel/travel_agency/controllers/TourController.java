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

    @GetMapping("/tours/all")
    public List<Tour> findAll() {
        return tourService.findAll();
    }
    @GetMapping("/tours/delete/all")
    public ModelAndView deleteAll() {
        tourService.deleteAll();
        return new ModelAndView(new RedirectView("/admin"));
    }
//    @GetMapping("/tour/delete/{id}")
//    public ModelAndView deleteOne(@PathVariable String id) {
//        tourService.deleteOne(Integer.parseInt(id));
//        return new ModelAndView(new RedirectView("/admin"));
//    }
    @PostMapping("/tours/delete")
    public ModelAndView deleteSome(@RequestParam(value = "checked", required = false) int[]checked) {
        if(checked != null) {
            for (int j : checked) {
                if (tourService.findOne(j) != null) {
                    tourService.deleteOne(j);
                }
            }
        }
        return new ModelAndView(new RedirectView("/admin"));
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
        Tour tour = new Tour(1450, new Date(), new Date(), new City("nameOfCity", new Country("nameOfCountry")));
        countryService.saveNewCountry(tour.getCity().getCountry());
        cityService.saveNewCity(tour.getCity());
        tourService.saveNewTour(tour);
    }
    @PostMapping("/new_tour_form")
    public ModelAndView newTourFormPost(@ModelAttribute("tour") Tour tour){
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
    public  List<Tour> findByDateFromAfterAndDateToBeforeAndCityContainsAndPriceLessThan
            (Date dateFrom, Date dateTo, City city, Integer price){
        return tourService.findByDateFromAfterAndDateToBeforeAndCityContainsAndPresentSeaAndPriceLessThan
                (dateFrom, dateTo, city, price);
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
