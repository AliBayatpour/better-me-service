package better_me_service.better_me.category.application;

import better_me_service.better_me.category.domain.model.Category;
import lombok.Data;
import java.util.UUID;

@Data
public class CategoryResponse {
    private final UUID id;
    private final String name;
    private final String color;
    private final UUID userId;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.color = category.getColor();
        this.userId = category.getUserId();
    }
}
