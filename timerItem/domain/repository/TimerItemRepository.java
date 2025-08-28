package better_me_service.better_me.timerItem.domain.repository;

import better_me_service.better_me.timerItem.domain.model.TimerItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TimerItemRepository  {
    Optional<TimerItem> findById(UUID id);
    List<TimerItem> findAllByUserId(UUID userId);
    TimerItem save(TimerItem timerItemEntity);
    void deleteById(UUID id);
}