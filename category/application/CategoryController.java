package better_me_service.better_me.category.application;

import better_me_service.better_me.category.domain.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable UUID id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        return categoryOptional.map(category -> ResponseEntity.ok(new CategoryResponse(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategoriesByUserId(@RequestParam UUID userId) {
        List<Category> categories = categoryService.findAllByUser(userId);
        return ResponseEntity.ok(categories.stream()
                .map(CategoryResponse::new)
                .toList());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        Category savedCategory = categoryService.save(categoryRequest);
        return new ResponseEntity<>(new CategoryResponse(savedCategory), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable UUID id, @RequestBody CategoryRequest categoryRequest) {
        try {
            Category updatedCategory = categoryService.update(id, categoryRequest);
            return ResponseEntity.ok(new CategoryResponse(updatedCategory));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}