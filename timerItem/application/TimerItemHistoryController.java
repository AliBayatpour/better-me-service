package better_me_service.better_me.timerItem.application;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import better_me_service.better_me.timerItem.domain.model.TimerItemHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/timer-item-histories")
public class TimerItemHistoryController {
    private final TimerItemHistoryService timerItemHistoryService;

    public TimerItemHistoryController(TimerItemHistoryService timerItemHistoryService) {
        this.timerItemHistoryService = timerItemHistoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimerItemHistoryResponse> getTimerItemHistory(@PathVariable UUID id) {
        Optional<TimerItemHistory> timerItemOptional = timerItemHistoryService.findById(id);
        return timerItemOptional.map(timerItem -> ResponseEntity.ok(new TimerItemHistoryResponse(timerItem)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TimerItemHistoryResponse>> getTimerItemHistoriesByUserId(@RequestParam UUID userId) {
        List<TimerItemHistory> timerItems = timerItemHistoryService.findAllByUser(userId);
        return ResponseEntity.ok(timerItems.stream()
                .map(TimerItemHistoryResponse::new)
                .toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimerItemHistory(@PathVariable UUID id) {
        timerItemHistoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}