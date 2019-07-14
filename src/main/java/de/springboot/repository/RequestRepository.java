package de.springboot.repository;

import de.springboot.model.RepairRequest;
import de.springboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<RepairRequest, Long> {
    RequestRepository findById(int id);
    RequestRepository findByUser(User user);
}
