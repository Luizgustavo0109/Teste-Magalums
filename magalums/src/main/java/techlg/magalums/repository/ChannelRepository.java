package techlg.magalums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techlg.magalums.entity.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
