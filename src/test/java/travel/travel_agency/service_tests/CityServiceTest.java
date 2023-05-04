package travel.travel_agency.service_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import travel.travel_agency.entities.City;
import travel.travel_agency.entities.Country;
import travel.travel_agency.repositories.CityRepository;
import travel.travel_agency.services.CityService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {
    @Mock
    private CityRepository repository;
    @Captor
    private ArgumentCaptor<City> captor;
    @Test
    public void saveNewCity(){
        City city = new City("NameOfCity", new Country("NameOfCountry"));
        CityService service = new CityService(repository);
        service.saveNewCity(city);
        Mockito.when(repository.findAllByOrderByNameAsc()).thenReturn(List.of(city));
        Mockito.verify(repository).save(captor.capture());
        City captured = captor.getValue();
        Assertions.assertEquals("NameOfCountry", captured.getCountry().getName());
        Assertions.assertEquals("NameOfCity", captured.getName());
        Assertions.assertEquals(1, service.findAllByOrderByName().size());
    }
    @Test
    public void deleteAll(){
        CityService service = new CityService(repository);
        service.deleteAll();
        Mockito.when(repository.findAllByOrderByNameAsc()).thenReturn(List.of());
        Assertions.assertEquals(0, service.findAllByOrderByName().size());
    }
    @Test
    public void findByName(){
        City city = new City("NameOfCity", new Country("NameOfCountry"));
        CityService service = new CityService(repository);
        service.saveNewCity(city);
        Mockito.when(repository.findByName("NameOfCity")).thenReturn(city);
        Assertions.assertEquals("NameOfCity", service.findByName("NameOfCity").getName());
        service.deleteAll();
        Mockito.when(repository.findByName("NameOfCity")).thenReturn(null);
        Assertions.assertNull(service.findByName("NameOfCity"));
    }
    @Test
    public void findAllByOrderByName(){
        City city1 = new City("NameOfCity1", new Country("NameOfCountry"));
        City city2 = new City("NameOfCity3", new Country("NameOfCountry"));
        City city3 = new City("NameOfCity2", new Country("NameOfCountry"));
        CityService service = new CityService(repository);
        Mockito.when(repository.findAllByOrderByNameAsc()).thenReturn(List.of());
        Assertions.assertEquals(0, service.findAllByOrderByName().size());
        service.saveNewCity(city1);
        service.saveNewCity(city2);
        service.saveNewCity(city3);
        Mockito.when(repository.findAllByOrderByNameAsc()).thenReturn(List.of(city1,city2,city3));
        Assertions.assertEquals(3, service.findAllByOrderByName().size());
    }

}
