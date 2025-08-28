package better_me_service.better_me.timerItem.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.UUID;

public record TimerItemRequest(
        @NotNull(message = "User ID cannot be null")
        UUID userId,

        @NotNull(message = "Category ID cannot be null")
        UUID categoryId,

        @Size(max = 400, message = "Description cannot exceed 400 characters")
        String description,

        OffsetDateTime finishedAt,

        @NotBlank(message = "Sort cannot be blank")
        @Size(max = 200, message = "Sort cannot exceed 200 characters")
        String sort,

        @NotNull(message = "Done status cannot be null")
        Boolean done,

        @NotNull(message = "Goal cannot be null")
        Integer goal,

        Integer progress
) {}