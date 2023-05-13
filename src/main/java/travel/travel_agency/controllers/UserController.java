package travel.travel_agency.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import travel.travel_agency.entities.*;
import travel.travel_agency.services.CityService;
import travel.travel_agency.services.CountryService;
import travel.travel_agency.services.TourService;
import travel.travel_agency.services.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final CityService cityService;
    private final CountryService countryService;
    private final TourService tourService;


    @GetMapping("/registration")
    @ModelAttribute
    public ModelAndView registration(Model model) {
        var user = new User();
        model.addAttribute("user", user);
        return new ModelAndView("/registration");
    }

    @PostMapping(path = "/registration")
    public ModelAndView register(
            @ModelAttribute("user") User user
    ) {
        try {
            service.saveUser(user);
            return new ModelAndView(new RedirectView("/profile"));
        } catch (Exception e) {
            return new ModelAndView("/registration");
        }
    }

    @GetMapping("/login")
    @ModelAttribute
    public ModelAndView login(Model model) {
        var user = new User();
        model.addAttribute("user", user);
        return new ModelAndView("/login");

    }

    @GetMapping("/profile")
    @ModelAttribute
    public ModelAndView profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority grantedAuthority: authentication.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals("ADMIN")) {
                return new ModelAndView(new RedirectView("/admin"));
            }
        }
        model.addAttribute("user", service.loadUserByUsername(authentication.getName()));
        model.addAttribute("tours", tourService.findAllByBought( service.loadUser(authentication.getName())));
        return new ModelAndView("/profile");
    }
    @GetMapping("/admin")
    @ModelAttribute
    public ModelAndView admin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", service.loadUserByUsername(authentication.getName()));
        model.addAttribute("tours", tourService.findAll());

        return new ModelAndView("/admin");
    }

    @ModelAttribute
    @GetMapping("/buy_new_tour")
    public ModelAndView buyNewTourGet(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = service.loadUser(authentication.getName());
        model.addAttribute("user", user);
//        Map<Tour, Boolean> map = new HashMap<>();
//        for (int i = 0; i < tourService.findAllByBought(null).size(); i++) {
//            map.put(tourService.findAllByBought(null).get(i), false);
//        }
//        model.addAttribute("map", map);

        model.addAttribute("tours", tourService.findAllByBought(null));
        return new ModelAndView("/buy_new_tour");
    }

    @ModelAttribute
    @PostMapping("/buy_new_tour/{id}")
    public ModelAndView buyNewTourPost(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = service.loadUser(authentication.getName());
        service.boughtTourById(Integer.parseInt(id), user.getId() );
        return new ModelAndView(new RedirectView("/buy_new_tour"));
    }

    @PostConstruct
    public void saveAdmin() {
        User admin = new User("admin@admin", "admin",Role.ADMIN);
        admin.setName("ADMIN");
        service.saveUser(admin);
        User user = new User("user@user", "user",Role.USER);
        user.setName("USER");
        Country country = new Country("newCountry");
        countryService.saveNewCountry(country);
        City city = new City("newCity",country);
        cityService.saveNewCity(city);
        Tour tour = new Tour(1460, new Date(), new Date(), city);
        tourService.saveNewTour(tour);
        service.saveUser(user);
        service.boughtTourById(tour.getId(),user.getId());

    }
}
