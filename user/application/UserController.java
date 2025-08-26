package better_me_service.better_me.user.application;

import better_me_service.better_me.user.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = new User(UUID.randomUUID(), userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(new UserResponse(createdUser));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(new UserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
