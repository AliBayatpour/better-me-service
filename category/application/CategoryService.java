package better_me_service.better_me.category.application;

import better_me_service.better_me.category.domain.model.Category;
import better_me_service.better_me.category.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
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
        return categoryRepository.update(category);
    }
}
