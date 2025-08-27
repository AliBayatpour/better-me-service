package better_me_service.better_me.category.domain.repository;

import better_me_service.better_me.category.domain.model.Category;
import better_me_service.better_me.user.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Optional<Category> findById(UUID id);
    List<Category> findAllByUser(User user);
    Category save(Category category);
    void deleteById(UUID id);
}
