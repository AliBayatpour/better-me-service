package better_me_service.better_me.category.infrastructure.persistence;

import better_me_service.better_me.category.domain.model.Category;
import better_me_service.better_me.category.domain.repository.CategoryRepository;
import better_me_service.better_me.user.domain.model.User;
import better_me_service.better_me.user.infrastructure.persistence.JpaUserRepository;
import better_me_service.better_me.user.infrastructure.persistence.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JpaCategoryRepository jpaCategoryRepository;
    private final JpaUserRepository jpaUserRepository;

    public CategoryRepositoryImpl(JpaCategoryRepository jpaCategoryRepository, JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return jpaCategoryRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Category> findAllByUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());

        List<CategoryEntity> categoryEntities = jpaCategoryRepository.findAllByUser(userEntity);
        return categoryEntities.stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = toEntity(category);
        CategoryEntity savedEntity = jpaCategoryRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Category update(Category category) {
        CategoryEntity entity = toEntity(category);
        CategoryEntity updatedEntity = jpaCategoryRepository.save(entity);
        return toDomain(updatedEntity);
    }

    @Override
    public void deleteById(UUID id) {
        jpaCategoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaCategoryRepository.existsById(id);
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
        UserEntity userEntity = jpaUserRepository.findById(category.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + category.getUserId()));

        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setColor(category.getColor());
        entity.setUser(userEntity);
        return entity;
    }
}