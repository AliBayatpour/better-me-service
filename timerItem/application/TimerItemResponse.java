package better_me_service.better_me.timerItem.application;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import lombok.Value;

import java.time.OffsetDateTime;
import java.util.UUID;

@Value
public class TimerItemResponse {
    UUID id;
    UUID userId;
    UUID categoryId;
    String description;
    OffsetDateTime finishedAt;
    String sort;
    Boolean done;
    Integer goal;
    Integer progress;

    public TimerItemResponse(TimerItem timerItemEntity) {
        this.id = timerItemEntity.getId();
        this.userId = timerItemEntity.getUserId();
        this.categoryId = timerItemEntity.getCategoryId();
        this.description = timerItemEntity.getDescription();
        this.finishedAt = timerItemEntity.getFinishedAt();
        this.sort = timerItemEntity.getSort();
        this.done = timerItemEntity.getDone();
        this.goal = timerItemEntity.getGoal();
        this.progress = timerItemEntity.getProgress();
    }
}