package better_me_service.better_me.user.application;

import better_me_service.better_me.user.domain.model.User;
import lombok.Value;

import java.util.UUID;

@Value
public class UserResponse {
     UUID id;
     String name;
     String email;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
