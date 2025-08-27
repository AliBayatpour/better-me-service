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

    public Category save(CategoryRequest categoryRequest) {
        Category category = new Category(
                null,
                categoryRequest.name(),
                categoryRequest.color(),
                categoryRequest.userId()
        );
        return categoryRepository.save(category);
    }

    public Category update(UUID id, CategoryRequest categoryRequest) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        Category updatedCategory = existingCategory
                .withName(categoryRequest.name())
                .withColor(categoryRequest.color());

        return categoryRepository.save(updatedCategory);
    }

    public void deleteById(UUID id) {
        categoryRepository.deleteById(id);
    }
}