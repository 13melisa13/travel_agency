package travel.travel_agency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.travel_agency.entities.User;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
   User findUserById(Integer id);
   User findByEmail(String email);
}
