package better_me_service.better_me.timerItem.domain.repository;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import better_me_service.better_me.user.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TimerItemRepository  {
    Optional<TimerItem> findById(UUID id);
    List<TimerItem> findAllByUser(User user);
    TimerItem save(TimerItem timerItemEntity);
    void deleteById(UUID id);
}