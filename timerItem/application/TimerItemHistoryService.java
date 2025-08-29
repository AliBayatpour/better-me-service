package better_me_service.better_me.timerItem.application;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import better_me_service.better_me.timerItem.domain.model.TimerItemHistory;
import better_me_service.better_me.timerItem.domain.repository.TimerItemHistoryRepository;
import better_me_service.better_me.timerItem.domain.repository.TimerItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TimerItemHistoryService {
    private final TimerItemHistoryRepository timerItemHistoryRepository;

    public TimerItemHistoryService(TimerItemHistoryRepository timerItemHistoryRepository) {
        this.timerItemHistoryRepository = timerItemHistoryRepository;
    }

    public Optional<TimerItemHistory> findById(UUID id) {
        return timerItemHistoryRepository.findById(id);
    }

    public List<TimerItemHistory> findAllByUser(UUID userId) {
        return timerItemHistoryRepository.findAllByUserId(userId);
    }

    public TimerItemHistory save(TimerItemHistoryRequest timerItemHistoryRequest) {
        TimerItemHistory timerItemHistory = new TimerItemHistory(
                null,
                timerItemHistoryRequest.userId(),
                timerItemHistoryRequest.categoryId(),
                timerItemHistoryRequest.description(),
                timerItemHistoryRequest.finishedAt(),
                timerItemHistoryRequest.sort(),
                true,
                timerItemHistoryRequest.goal(),
                timerItemHistoryRequest.progress()
        );
        return timerItemHistoryRepository.save(timerItemHistory);
    }

    public TimerItemHistory update(UUID id, TimerItemHistoryRequest timerItemRequest) {
        TimerItemHistory existingTimerItem = timerItemHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TimerItem not found with id: " + id));

        TimerItemHistory updatedTimerItem = existingTimerItem
                .withUserId(timerItemRequest.userId())
                .withCategoryId(timerItemRequest.categoryId())
                .withDescription(timerItemRequest.description())
                .withFinishedAt(timerItemRequest.finishedAt())
                .withSort(timerItemRequest.sort())
                .withDone(timerItemRequest.done())
                .withGoal(timerItemRequest.goal())
                .withProgress(timerItemRequest.progress());

        return timerItemHistoryRepository.save(updatedTimerItem);
    }

    public void deleteById(UUID id) {
        timerItemHistoryRepository.deleteById(id);
    }
}
