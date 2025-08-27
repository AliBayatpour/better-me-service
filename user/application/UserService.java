package better_me_service.better_me.user.application;

import better_me_service.better_me.user.domain.model.User;
import better_me_service.better_me.user.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest userRequest) {
        String hashedPassword = passwordEncoder.encode(userRequest.password());
        User user = new User(null, userRequest.name(), userRequest.email(), hashedPassword);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
