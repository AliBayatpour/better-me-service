package better_me_service.better_me.category.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable UUID id) {
       return categoryService.findById(id)
               .map(category -> ResponseEntity.ok(new CategoryResponse(category)))
               .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getCategoryByUserId(@RequestParam UUID userId) {
        return categoryService.findByUserId(userId)
                .map(category -> ResponseEntity.ok(new CategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }
}
