package travel.travel_agency.service_tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import travel.travel_agency.entities.Role;
import travel.travel_agency.entities.User;
import travel.travel_agency.repositories.UserRepository;
import travel.travel_agency.services.UserService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder encoder;
    @Captor
    private ArgumentCaptor<User> captor;
    @Test
    public void getUsers() {
        User user1 = new User("email1", "password1", Role.USER);
        User user2 = new User("email2", "password2", Role.USER);
        User user3 = new User("email3", "password3", Role.USER);
        UserService userService = new UserService(encoder,userRepository);
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user1,user2,user3));
        Assertions.assertEquals("email1", userService.getUsers().get(0).getEmail());
        Assertions.assertEquals(3, userService.getUsers().size());
    }
    @Test
    public void saveUser() {
        User user = new User("email1", "password1", Role.USER);
        UserService userService = new UserService(encoder,userRepository);
        Mockito.when(encoder.encode(user.getPassword())).thenReturn("password");
        userService.saveUser(user);
        Mockito.verify(userRepository).save(captor.capture());
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        User captured = captor.getValue();
        System.out.println(captured.getPassword());
        Assertions.assertEquals("email1", captured.getEmail());
        Assertions.assertEquals(1, userService.getUsers().size());
    }

    @Test
    public void loadUserByUsername(){
        User user1 = new User("email1", "", Role.USER);
        Mockito.when(userRepository.findByEmail(user1.getEmail())).thenReturn(user1);
        UserService userService = new UserService(encoder,userRepository);
        Assertions.assertEquals(user1,userService.loadUserByUsername(user1.getUsername()));
    }
}

