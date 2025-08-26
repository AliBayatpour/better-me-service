package better_me_service.better_me.timerItem.domain.repository;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import better_me_service.better_me.timerItem.infrastructure.persistence.TimerItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TimerItemRepository  {
    Optional<TimerItem> findById(UUID id);
    TimerItem save(TimerItem timerItemEntity);
    TimerItem update(TimerItem timerItemEntity);
    void deleteById(UUID id);


}