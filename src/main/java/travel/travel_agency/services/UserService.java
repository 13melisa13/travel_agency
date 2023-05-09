package travel.travel_agency.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import travel.travel_agency.entities.Role;
import travel.travel_agency.entities.User;
import travel.travel_agency.repositories.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public List<User> getUsers(){
        return repository.findAll();
    }
    public void saveUser(User user) {
        if(repository.findByEmail(user.getEmail()) == null) {
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(user);
            log.info("Saved new user");


        } else {
            log.info("Not saved new user");
            throw new RuntimeException("Такой пользователь уже существует");
        }
    }

    @PostConstruct
    public void saveAdmin() {
        User admin = new User("admin@admin", passwordEncoder.encode("admin"),Role.ADMIN);
        admin.setName("ADMIN");
        repository.save(admin);
        log.info(admin.toString());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(repository.findByEmail(username) != null) {
            log.info("Found user with username {}",username);
            return repository.findByEmail(username);
        }
        else {
            log.info("Not found the user");
            throw new UsernameNotFoundException("Not found the user");
        }
    }
}
