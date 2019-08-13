package de.springboot.repository;

import de.springboot.model.Specification;
import de.springboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    User findById(int id);
    List<User> findAllBySpecifications(Specification specification);
}
