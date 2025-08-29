package better_me_service.better_me.timerItem.application;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import better_me_service.better_me.timerItem.domain.repository.TimerItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TimerItemService {
    private final TimerItemRepository timerItemRepository;

    public TimerItemService(TimerItemRepository timerItemRepository) {
        this.timerItemRepository = timerItemRepository;
    }

    public Optional<TimerItem> findById(UUID id) {
        return timerItemRepository.findById(id);
    }

    public List<TimerItem> findAllByUser(UUID userId) {
        return timerItemRepository.findAllByUserId(userId);
    }

    public TimerItem save(TimerItemRequest timerItemRequest) {
        TimerItem timerItem = new TimerItem(
                null,
                timerItemRequest.userId(),
                timerItemRequest.categoryId(),
                timerItemRequest.description(),
                timerItemRequest.finishedAt(),
                timerItemRequest.sort(),
                timerItemRequest.done(),
                timerItemRequest.goal(),
                timerItemRequest.progress()
        );
        return timerItemRepository.save(timerItem);
    }

    public TimerItem update(UUID id, TimerItemRequest timerItemRequest) {
        TimerItem existingTimerItem = timerItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TimerItem not found with id: " + id));

        TimerItem updatedTimerItem = existingTimerItem
                .withUserId(timerItemRequest.userId())
                .withCategoryId(timerItemRequest.categoryId())
                .withDescription(timerItemRequest.description())
                .withFinishedAt(timerItemRequest.finishedAt())
                .withSort(timerItemRequest.sort())
                .withDone(timerItemRequest.done())
                .withGoal(timerItemRequest.goal())
                .withProgress(timerItemRequest.progress());

        return timerItemRepository.save(updatedTimerItem);
    }

    public void deleteById(UUID id) {
        timerItemRepository.deleteById(id);
    }
}