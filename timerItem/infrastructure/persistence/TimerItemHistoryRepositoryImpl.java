package better_me_service.better_me.timerItem.infrastructure.persistence;

import better_me_service.better_me.category.infrastructure.persistence.CategoryEntity;
import better_me_service.better_me.category.infrastructure.persistence.JpaCategoryRepository;
import better_me_service.better_me.timerItem.domain.model.TimerItem;
import better_me_service.better_me.timerItem.domain.model.TimerItemHistory;
import better_me_service.better_me.timerItem.domain.repository.TimerItemHistoryRepository;
import better_me_service.better_me.user.infrastructure.persistence.JpaUserRepository;
import better_me_service.better_me.user.infrastructure.persistence.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TimerItemHistoryRepositoryImpl implements TimerItemHistoryRepository {
    private final JpaTimerItemHistoryRepository jpaTimerItemHistoryRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaCategoryRepository jpaCategoryRepository;

    public TimerItemHistoryRepositoryImpl(JpaTimerItemHistoryRepository jpaTimerItemHistoryRepository, JpaUserRepository jpaUserRepository, JpaCategoryRepository jpaCategoryRepository) {
        this.jpaTimerItemHistoryRepository = jpaTimerItemHistoryRepository;
        this.jpaUserRepository = jpaUserRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public Optional<TimerItemHistory> findById(UUID id) {
        return jpaTimerItemHistoryRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<TimerItemHistory> findAllByUserId(UUID userId) {
        UserEntity userEntity = jpaUserRepository.getReferenceById(userId);
        List<TimerItemHistoryEntity> timerItemHistoryEntities = jpaTimerItemHistoryRepository.findAllByUser(userEntity);
        return timerItemHistoryEntities.stream().map(this::toDomain).toList();
    }

    @Override
    public TimerItemHistory save(TimerItemHistory timerItem) {
        TimerItemHistoryEntity entity = toEntity(timerItem);
        TimerItemHistoryEntity savedEntity = jpaTimerItemHistoryRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaTimerItemHistoryRepository.deleteById(id);
    }

    public TimerItemHistory toDomain(TimerItemHistoryEntity entity) {
        return new TimerItemHistory(
                entity.getId(),
                entity.getUser().getId(),
                entity.getCategory() != null ? entity.getCategory().getId() : null, // Handle potential null category
                entity.getDescription(),
                entity.getFinishedAt(),
                entity.getSort(),
                entity.getDone(),
                entity.getGoal(),
                entity.getProgress()
        );
    }

    private TimerItemHistoryEntity toEntity(TimerItemHistory timerItem) {
        UserEntity userEntity = jpaUserRepository.findById(timerItem.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + timerItem.getUserId()));
        CategoryEntity categoryEntity = jpaCategoryRepository.findById(timerItem.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + timerItem.getUserId()));
        TimerItemHistoryEntity entity = new TimerItemHistoryEntity();
        entity.setId(timerItem.getId());
        entity.setDescription(timerItem.getDescription());
        entity.setFinishedAt(timerItem.getFinishedAt());
        entity.setSort(timerItem.getSort());
        entity.setDone(timerItem.getDone());
        entity.setGoal(timerItem.getGoal());
        entity.setProgress(timerItem.getProgress());
        entity.setUser(userEntity);
        entity.setCategory(categoryEntity);
        return entity;
    }
}