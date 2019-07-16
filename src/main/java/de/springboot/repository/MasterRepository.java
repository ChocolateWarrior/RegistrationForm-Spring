package de.springboot.repository;

import de.springboot.model.Master;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends CrudRepository<Master, Long> {
    Master findByLoginAndPassword(String login, String password);
    Master findById(int id);
}
