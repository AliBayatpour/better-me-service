package better_me_service.better_me.user.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@With
public class User {
    private  UUID id;
    private String name;
    private String email;
    private String password;
}
