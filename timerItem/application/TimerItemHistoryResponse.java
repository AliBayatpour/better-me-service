package better_me_service.better_me.timerItem.application;

import better_me_service.better_me.timerItem.domain.model.TimerItem;
import better_me_service.better_me.timerItem.domain.model.TimerItemHistory;

import java.time.OffsetDateTime;
import java.util.UUID;

public class TimerItemHistoryResponse {
    UUID id;
    UUID userId;
    UUID categoryId;
    String description;
    OffsetDateTime finishedAt;
    String sort;
    Boolean done;
    Integer goal;
    Integer progress;

    public TimerItemHistoryResponse(TimerItemHistory timerItemHistoryEntity) {
        this.id = timerItemHistoryEntity.getId();
        this.userId = timerItemHistoryEntity.getUserId();
        this.categoryId = timerItemHistoryEntity.getCategoryId();
        this.description = timerItemHistoryEntity.getDescription();
        this.finishedAt = timerItemHistoryEntity.getFinishedAt();
        this.sort = timerItemHistoryEntity.getSort();
        this.done = timerItemHistoryEntity.getDone();
        this.goal = timerItemHistoryEntity.getGoal();
        this.progress = timerItemHistoryEntity.getProgress();
    }
}
