package travel.travel_agency.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import travel.travel_agency.entities.Role;
import travel.travel_agency.entities.User;
import travel.travel_agency.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;


    @GetMapping("/registration")
    @ModelAttribute
    public String registration(Model model) {
        var user =new User();
        user.setRole(Role.USER);
        model.addAttribute("user",user );
        return "registration";
    }
    @PostMapping(path="/registration")
    public String register(
             @ModelAttribute("user")User user
    ){
        try {
            service.saveUser(user);
            return "login";
        } catch (Exception e){
            return "redirect:/registration";
        }
    }
    @GetMapping("/login")
    @ModelAttribute
    public String login(Model model) {
        var user =new User();
        model.addAttribute("user", user);
        return "login";
    }
    @PostMapping("/login")
    public String authenticate(
            @ModelAttribute("user")User user
    ){
        try {
            service.authenticate(user);
            return "redirect:/profile";
        } catch (Exception e){
            return "login";
        }
    }

    @GetMapping("/")
    public String index() {
        return "profile";
    }
}
