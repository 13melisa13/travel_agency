package travel.travel_agency.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import travel.travel_agency.entities.Country;
import travel.travel_agency.services.CountryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;
    @GetMapping("/country/all")
    public List<Country> findAll() {
        return service.findAllByOrderByName();
    }
    @DeleteMapping("/country/all")
    public void deleteAll() {
        service.deleteAll();
    }
    @GetMapping("/country/one")
    public Country findByName(@RequestParam String name) {
        return service.findByName(name);
    }
    @PostMapping(path= "/country/one")
    public void save(@RequestParam Country country){
        service.saveNewCountry(country);
    }
}
