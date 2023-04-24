package travel.travel_agency.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travel.travel_agency.entities.Country;
import travel.travel_agency.repositories.CountryRepository;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CityService {
    private final CountryRepository repository;
    public void saveNewCity(Country country){
        repository.save(country);
    }
    public void deleteAll(Country country){
        repository.deleteAll();
    }
    public Country findByName(String name){
        return repository.findByName(name);
    }
}
