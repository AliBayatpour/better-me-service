package better_me_service.better_me.timerItem.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTimerItemRepository extends JpaRepository<TimerItemEntity, UUID> {
}