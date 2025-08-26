package better_me_service.better_me.category.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private UUID id;
    private String name;
    private String color;
    private UUID userId;
}
