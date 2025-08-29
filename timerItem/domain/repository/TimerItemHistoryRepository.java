package better_me_service.better_me.timerItem.domain.repository;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import better_me_service.better_me.timerItem.domain.model.TimerItemHistory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TimerItemHistoryRepository {
    Optional<TimerItemHistory> findById(UUID id);
    List<TimerItemHistory> findAllByUserId(UUID userId);
    TimerItemHistory save(TimerItemHistory timerItemEntity);
    void deleteById(UUID id);
}
