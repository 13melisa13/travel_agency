package travel.travel_agency.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travel.travel_agency.entities.City;
import travel.travel_agency.repositories.CityRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CityService {
    private final CityRepository repository;
    public void saveNewCity(City city){
        if (repository.findByName(city.getName()) == null) {
            repository.save(city);
            log.info("New city was saved {}", city);
        }
    }
    public void deleteAll(){
        repository.deleteAll();
        log.info("All cities were deleted");
    }
    public City findByName(String name){
        log.info("City was found by name {}",name);
        return repository.findByName(name);
    }
    public List<City> findAllByOrderByName(){
        log.info("All cities were found");
        return repository.findAllByOrderByNameAsc();
    }
}
