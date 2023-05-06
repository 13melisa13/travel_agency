package travel.travel_agency.controllers;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import travel.travel_agency.entities.Role;
import travel.travel_agency.entities.User;
import travel.travel_agency.services.UserService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService service;


    @GetMapping("/registration")
    @ModelAttribute
    public ModelAndView registration(Model model) {
        var user =new User();
        user.setRole(Role.USER);
        model.addAttribute("user",user );
        return new ModelAndView("/registration");
    }
    @PostMapping(path="/registration")
    public ModelAndView register(
             @ModelAttribute("user")User user
    ){
        try {
            service.saveUser(user);
            return new ModelAndView(new RedirectView("/profile"));
        } catch (Exception e){
            return new ModelAndView("/registration");
        }
    }
    @GetMapping("/login")
    @ModelAttribute
    public ModelAndView login(Model model) {
        var user =new User();
        model.addAttribute("user", user);
        return new ModelAndView("/login");

    }
    @PostMapping("/login")
    public ModelAndView authenticate(
            @ModelAttribute("user")User user
    ){
        try {
            service.authenticate(user);
            service.loadUserByUsername(user.getUsername());
            System.out.println("reSult ok");
            log.info("res ok");
            return new ModelAndView(new RedirectView("/profile"));
        } catch (Exception e){
            System.out.println(e.getMessage());
            log.info(e.getMessage());
            return new ModelAndView("/login");
        }
    }

    @GetMapping("/profile")
    @ModelAttribute
    public ModelAndView profile(Model model) {
        model.getAttribute("user");
        return new ModelAndView("/profile");
    }
}
