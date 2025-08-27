package better_me_service.better_me.user.domain.repository;

import better_me_service.better_me.user.domain.model.User;
import better_me_service.better_me.user.infrastructure.persistence.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);
    User save(User user);
    Optional<UserEntity> findUserEntityById(UUID userId);
    void deleteById(UUID id);
}
