package better_me_service.better_me.timerItem.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimerItem {
    private UUID id;
    private UUID userId;
    private UUID categoryId;
    private String description;
    private OffsetDateTime finishedAt;
    private String sort;
    private Boolean done;
    private Integer goal;
    private Integer progress;


}