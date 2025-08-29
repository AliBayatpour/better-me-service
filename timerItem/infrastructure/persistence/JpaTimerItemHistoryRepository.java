package better_me_service.better_me.timerItem.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaTimerItemHistoryRepository extends JpaRepository<TimerItemHistoryEntity, UUID> {
    List<TimerItemHistoryEntity> findByTimerItemHistoryId(UUID timerItemId);
}
