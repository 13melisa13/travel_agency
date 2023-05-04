package travel.travel_agency.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import travel.travel_agency.entities.City;
import travel.travel_agency.services.CityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController {
    private final CityService service;

    @GetMapping("/city/all")
    public List<City> findAll() {
        return service.findAllByOrderByName();
    }
    @DeleteMapping("/city/all")
    public void deleteAll() {
        service.deleteAll();
    }
    @GetMapping("/city/one")
    public City findByName(@RequestParam String name) {
        return service.findByName(name);
    }
    @PostMapping(path= "/city/one")
    public void save(@RequestParam City city){
        service.saveNewCity(city);
    }
}
