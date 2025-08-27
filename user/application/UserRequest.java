package better_me_service.better_me.user.application;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Name cannot be empty") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @NotBlank(message = "Email cannot be empty") @Email(message = "Email should be a valid email address")
        String email,

        @NotBlank(message = "Password cannot be empty") @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {}
