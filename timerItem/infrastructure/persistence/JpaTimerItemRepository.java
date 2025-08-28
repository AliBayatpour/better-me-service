package better_me_service.better_me.timerItem.infrastructure.persistence;

import better_me_service.better_me.user.domain.model.User;
import better_me_service.better_me.user.infrastructure.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaTimerItemRepository extends JpaRepository<TimerItemEntity, UUID> {
    List<TimerItemEntity> findAllByUser(UserEntity user);
}