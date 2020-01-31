package helvetica.application.repositories;

import helvetica.application.entities.Specification;
import helvetica.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    User findById(int id);
    List<User> findAllBySpecifications(Specification specification);
}
