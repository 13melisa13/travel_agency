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
import travel.travel_agency.repositories.CountryRepository;
import travel.travel_agency.services.CityService;
import travel.travel_agency.services.CountryService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {
    @Mock
    private CountryRepository repository;
    @Captor
    private ArgumentCaptor<Country> captor;
    @Test
    public void saveNewCountry(){
        CountryService service = new CountryService(repository);
        City city1 = new City("NameOfCity1", new Country("NameOfCountry"));
        City city2 = new City("NameOfCity2", new Country("NameOfCountry"));
        Country country = new Country("NameOfCountry");
        service.saveNewCountry(country);
        Mockito.when(repository.findAllByOrderByNameAsc()).thenReturn(List.of(country));
        Mockito.verify(repository).save(captor.capture());
        Country captured = captor.getValue();
        Assertions.assertEquals("NameOfCountry", captured.getName());
        //Assertions.assertEquals(List.of(city1,city2), captured.getCities());
        Assertions.assertEquals(1, service.findAllByOrderByName().size());
    }
    @Test
    public void deleteAll(){
        CountryService service = new CountryService(repository);
        service.deleteAll();
        Mockito.when(repository.findAllByOrderByNameAsc()).thenReturn(List.of());
        Assertions.assertEquals(0, service.findAllByOrderByName().size());
    }
    @Test
    public void findByName(){
        Country country = new Country("NameOfCountry");
        CountryService service = new CountryService(repository);
        service.saveNewCountry(country);
        Mockito.when(repository.findByName("NameOfCountry")).thenReturn(country);
        Assertions.assertEquals("NameOfCountry", service.findByName("NameOfCountry").getName());
        service.deleteAll();
        Mockito.when(repository.findByName("NameOfCountry")).thenReturn(null);
        Assertions.assertNull(service.findByName("NameOfCountry"));
    }
    @Test
    public void findAllByOrderByName(){
        Country country1 = new Country("NameOfCountry1");
        Country country2 = new Country("NameOfCountry3");
        Country country3 = new Country("NameOfCountry2");
        CountryService service = new CountryService(repository);
        Mockito.when(repository.findAllByOrderByNameAsc()).thenReturn(List.of());
        Assertions.assertEquals(0, service.findAllByOrderByName().size());
        service.saveNewCountry(country1);
        service.saveNewCountry(country2);
        service.saveNewCountry(country3);
        Mockito.when(repository.findAllByOrderByNameAsc()).thenReturn(List.of(country1,country2,country3));
        Assertions.assertEquals(3, service.findAllByOrderByName().size());
    }

}
