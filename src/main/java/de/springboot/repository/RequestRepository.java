package de.springboot.repository;

import de.springboot.model.RepairRequest;
import de.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RepairRequest, Long> {
    RepairRequest findById(int id);
    List<RepairRequest> findByUser(User user);
    List<RepairRequest> findByMasters(User master);
}
