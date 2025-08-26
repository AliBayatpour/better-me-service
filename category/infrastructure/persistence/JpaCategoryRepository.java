package better_me_service.better_me.category.infrastructure.persistence;

import better_me_service.better_me.category.domain.model.Category;
import better_me_service.better_me.user.infrastructure.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    Optional<List<CategoryEntity>> findAllByUser(UserEntity user);
}
