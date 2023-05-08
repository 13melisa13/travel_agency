package travel.travel_agency.controllers;

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
import travel.travel_agency.entities.Role;
import travel.travel_agency.entities.Tour;
import travel.travel_agency.entities.User;
import travel.travel_agency.services.TourService;
import travel.travel_agency.services.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
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
    @GetMapping("/new_tour_form")
    @ModelAttribute
    public ModelAndView new_tour_form(Model model) {
        var tour = new Tour();
        model.addAttribute("tour", tour);
        return new ModelAndView("/new_tour_form");
    }

    @PostMapping(path = "/new_tour_form")
    public ModelAndView new_tour(
            @ModelAttribute("tour") Tour tour
    ) {
        try {
            tourService.saveNewTour(tour);
            return new ModelAndView(new RedirectView("/admin"));
        } catch (Exception e) {
            return new ModelAndView("/new_tour_form");
        }
    }

}
