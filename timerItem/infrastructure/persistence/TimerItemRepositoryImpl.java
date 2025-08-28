package better_me_service.better_me.timerItem.infrastructure.persistence;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import better_me_service.better_me.timerItem.domain.repository.TimerItemRepository;

import better_me_service.better_me.user.infrastructure.persistence.JpaUserRepository;
import better_me_service.better_me.user.infrastructure.persistence.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TimerItemRepositoryImpl implements TimerItemRepository {
    private final JpaTimerItemRepository jpaTimerItemRepository;
    private final JpaUserRepository jpaUserRepository;

    public TimerItemRepositoryImpl(JpaTimerItemRepository jpaTimerItemRepository, JpaUserRepository jpaUserRepository) {
        this.jpaTimerItemRepository = jpaTimerItemRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<TimerItem> findById(UUID id) {
        return jpaTimerItemRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<TimerItem> findAllByUserId(UUID userId) {
        UserEntity userEntity = jpaUserRepository.getReferenceById(userId);
        List<TimerItemEntity> timerItemEntities = jpaTimerItemRepository.findAllByUser(userEntity);
        return timerItemEntities.stream().map(this::toDomain).toList();
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
        UserEntity userEntity = jpaUserRepository.findById(timerItem.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + timerItem.getUserId()));

        TimerItemEntity entity = new TimerItemEntity();
        entity.setId(timerItem.getId());
        entity.setDescription(timerItem.getDescription());
        entity.setFinishedAt(timerItem.getFinishedAt());
        entity.setSort(timerItem.getSort());
        entity.setDone(timerItem.getDone());
        entity.setGoal(timerItem.getGoal());
        entity.setProgress(timerItem.getProgress());
        entity.setUser(userEntity);
        return entity;
    }
}