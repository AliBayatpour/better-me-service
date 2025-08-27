package better_me_service.better_me.category.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class CategoryRequest {
    @NotBlank(message = "Name cannot be blank")
    String name;

    @NotBlank(message = "Color cannot be blank")
    String color;

    @NotNull(message = "User ID cannot be null")
    UUID userId;
}