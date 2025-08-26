package better_me_service.better_me.category.infrastructure.persistence;

import better_me_service.better_me.category.domain.model.Category;
import better_me_service.better_me.category.domain.repository.CategoryRepository;
import better_me_service.better_me.user.domain.model.User;
import better_me_service.better_me.user.infrastructure.persistence.UserEntity;
import better_me_service.better_me.user.infrastructure.persistence.UserRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JpaCategoryRepository jpaCategoryRepository;
    private final UserRepositoryImpl userRepositoryImpl;

    public CategoryRepositoryImpl(JpaCategoryRepository jpaCategoryRepository, UserRepositoryImpl userRepositoryImpl) {
        this.jpaCategoryRepository = jpaCategoryRepository;
        this.userRepositoryImpl = userRepositoryImpl;
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return jpaCategoryRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<java.util.List<Category>> findAllByUser(UserEntity userEntity) {
        return jpaCategoryRepository.findAllByUser(userEntity)
                .map(categoryEntities -> categoryEntities.stream()
                        .map(this::toDomain)
                        .toList());
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = toEntity(category);
        CategoryEntity savedEntity = jpaCategoryRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Category update(Category category) {
        if (category.getId() == null) {
            throw new IllegalArgumentException("Category ID cannot be null for update.");
        }
        CategoryEntity entity = toEntity(category);
        CategoryEntity updatedEntity = jpaCategoryRepository.save(entity);
        return toDomain(updatedEntity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaCategoryRepository.deleteById(id);
    }

    public Category toDomain(CategoryEntity entity) {
        return new Category(
                entity.getId(),
                entity.getName(),
                entity.getColor(),
                entity.getUser().getId()
        );
    }

    private CategoryEntity toEntity(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setColor(category.getColor());
        UserEntity userEntity = userRepositoryImpl.findEntityById(category.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + category.getUserId()));

        entity.setUser(userEntity);
        return entity;
    }
}
