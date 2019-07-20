package de.springboot.repository;

import de.springboot.model.Master;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends CrudRepository<Master, Long> {
    Master findByUsername(String login);
    Master findByUsernameAndPassword(String login, String password);
    Master findById(int id);
}
