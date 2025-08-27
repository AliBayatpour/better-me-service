package better_me_service.better_me.timerItem.infrastructure.persistence;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import better_me_service.better_me.timerItem.domain.repository.TimerItemRepository;
import better_me_service.better_me.timerItem.infrastructure.persistence.JpaTimerItemRepository;
import better_me_service.better_me.timerItem.infrastructure.persistence.TimerItemEntity;
import better_me_service.better_me.user.infrastructure.persistence.UserEntity;
import better_me_service.better_me.user.infrastructure.persistence.UserRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class TimerItemRepositoryImpl implements TimerItemRepository {
    private final JpaTimerItemRepository jpaTimerItemRepository;
    private final UserRepositoryImpl userRepositoryImpl;

    public TimerItemRepositoryImpl(JpaTimerItemRepository jpaTimerItemRepository, TimerItemRepositoryImpl timerItemRepositoryImpl, UserRepositoryImpl userRepositoryImpl) {
        this.jpaTimerItemRepository = jpaTimerItemRepository;
        this.userRepositoryImpl = userRepositoryImpl;
    }

    @Override
    public Optional<TimerItem> findById(UUID id) {
        return jpaTimerItemRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public TimerItem save(TimerItem timerItem) {
        TimerItemEntity entity = toEntity(timerItem);
        TimerItemEntity savedEntity = jpaTimerItemRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaTimerItemRepository.deleteById(id);
    }

    public TimerItem toDomain(TimerItemEntity entity) {
        return new TimerItem(
                entity.getId(),
                entity.getUser().getId(),
                entity.getCategory().getId(),
                entity.getDescription(),
                entity.getFinishedAt(),
                entity.getSort(),
                entity.getDone(),
                entity.getGoal(),
                entity.getProgress()
        );
    }

    private TimerItemEntity toEntity(TimerItem timerItem) {
        TimerItemEntity entity = new TimerItemEntity();
        entity.setId(timerItem.getId());
        entity.setDescription(timerItem.getDescription());
        entity.setFinishedAt(timerItem.getFinishedAt());
        entity.setSort(timerItem.getSort());
        entity.setDone(timerItem.getDone());
        entity.setGoal(timerItem.getGoal());
        entity.setProgress(timerItem.getProgress());

        UserEntity userEntity = userRepositoryImpl.findEntityById(timerItem.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + timerItem.getUserId()));

        entity.setUser(userEntity);
        return entity;
    }
}