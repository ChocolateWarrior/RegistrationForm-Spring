package de.springboot.repository;

import de.springboot.model.Master;
import de.springboot.model.RepairRequest;
import de.springboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<RepairRequest, Long> {
    RepairRequest findById(int id);
    List<RepairRequest> findByUser(User user);
    RepairRequest findByMaster(Master master);
}
