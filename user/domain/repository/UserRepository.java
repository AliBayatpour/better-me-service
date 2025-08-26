package better_me_service.better_me.user.domain.repository;

import better_me_service.better_me.user.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);
    User save(User user);
    void deleteById(UUID id);
}
