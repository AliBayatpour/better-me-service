package better_me_service.better_me.user.application;

import better_me_service.better_me.user.domain.model.User;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {
    private final UUID id;
    private final String name;
    private final String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
