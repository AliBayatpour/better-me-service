package better_me_service.better_me.category.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@With
public class Category {
    private UUID id;
    private String name;
    private String color;
    private UUID userId;
}
