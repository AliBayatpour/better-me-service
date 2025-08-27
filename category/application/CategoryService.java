package better_me_service.better_me.category.application;

import better_me_service.better_me.category.domain.model.Category;
import better_me_service.better_me.category.domain.repository.CategoryRepository;
import better_me_service.better_me.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findAllByUser(UUID userId) {
        return userRepository.findById(userId)
                .map(categoryRepository::findAllByUser)
                .orElse(List.of());
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(UUID id) {
        categoryRepository.deleteById(id);
    }

    public Category update(Category category) {
        if (category.getId() == null) {
            throw new IllegalArgumentException("Category ID cannot be null for update.");
        }
        if (!categoryRepository.existsById(category.getId())) {
            throw new RuntimeException("Category not found with id: " + category.getId());
        }
        return categoryRepository.update(category);
    }
}