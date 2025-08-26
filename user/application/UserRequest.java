package better_me_service.better_me.user.application;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @Size(min = 3, max = 50)
    private String name;
    @Email
    private String email;
    private String password;
}
