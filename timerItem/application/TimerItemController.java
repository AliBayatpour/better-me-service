package better_me_service.better_me.timerItem.application;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/timer-items")
public class TimerItemController {
    private final TimerItemService timerItemService;

    public TimerItemController(TimerItemService timerItemService) {
        this.timerItemService = timerItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimerItemResponse> getTimerItem(@PathVariable UUID id) {
        Optional<TimerItem> timerItemOptional = timerItemService.findById(id);
        return timerItemOptional.map(timerItem -> ResponseEntity.ok(new TimerItemResponse(timerItem)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TimerItemResponse>> getTimerItemsByUserId(@RequestParam UUID userId) {
        List<TimerItem> timerItems = timerItemService.findAllByUser(userId);
        return ResponseEntity.ok(timerItems.stream()
                .map(TimerItemResponse::new)
                .toList());
    }

    @PostMapping
    public ResponseEntity<TimerItemResponse> saveTimerItem(@RequestBody TimerItemRequest timerItemRequest) {
        TimerItem savedTimerItem = timerItemService.save(timerItemRequest);
        return new ResponseEntity<>(new TimerItemResponse(savedTimerItem), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimerItemResponse> updateTimerItem(@PathVariable UUID id, @RequestBody TimerItemRequest timerItemRequest) {
        try {
            TimerItem updatedTimerItem = timerItemService.update(id, timerItemRequest);
            return ResponseEntity.ok(new TimerItemResponse(updatedTimerItem));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimerItem(@PathVariable UUID id) {
        timerItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}