package better_me_service.better_me.category.domain.repository;

import better_me_service.better_me.category.domain.model.Category;
import better_me_service.better_me.user.domain.model.User;
import better_me_service.better_me.user.infrastructure.persistence.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Optional<Category> findById(UUID id);
    List<Category> findAllByUser(User user);
    Category save(Category category);
    Category update(Category category);
    void deleteById(UUID id);
    boolean existsById(UUID id);

}
