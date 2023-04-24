package travel.travel_agency.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travel.travel_agency.entities.Country;
import travel.travel_agency.repositories.CountryRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CountryService {
    private final CountryRepository repository;
    public void saveNewCountry(Country country){
        repository.save(country);
    }
    public void deleteAll(Country country){
        repository.deleteAll();
    }
    public Country findByName(String name){
        return repository.findByName(name);
    }
    public Iterable<Country> findAll(){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        return repository.findAll();
    }
}
