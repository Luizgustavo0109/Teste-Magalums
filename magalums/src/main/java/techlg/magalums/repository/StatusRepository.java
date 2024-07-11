package techlg.magalums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techlg.magalums.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{
}
