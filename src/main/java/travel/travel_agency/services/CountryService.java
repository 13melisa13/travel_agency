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
    public void saveNewCity(Country country){
        repository.save(country);
        log.info("New country was saved {}",country);
    }
    public void deleteAll(Country country){
        repository.deleteAll();
        log.info("All countries were deleted");
    }
    public Country findByName(String name){
        log.info("Country was found by name {}",name);
        return repository.findByName(name);
    }
    public List<Country> findAllByOrderByName(String name){
        log.info("All countries were found");
        return repository.findAllByOrderByNameAsc();
    }

}
