package helvetica.application.repositories;

import helvetica.application.entities.RepairRequest;
import helvetica.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RepairRequest, Long> {
    RepairRequest findById(int id);
    List<RepairRequest> findByUser(User user);
    List<RepairRequest> findByMasters(User master);
}
