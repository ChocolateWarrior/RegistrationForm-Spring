package de.springboot.repository;

import de.springboot.model.RepairRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<RepairRequest, Long> {
    RequestRepository findById(int id);
}
