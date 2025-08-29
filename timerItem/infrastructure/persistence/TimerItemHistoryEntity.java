package better_me_service.better_me.timerItem.infrastructure.persistence;

import better_me_service.better_me.category.infrastructure.persistence.CategoryEntity;
import better_me_service.better_me.user.infrastructure.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "timer_items_histories")
@Getter
@Setter
public class TimerItemHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(length = 400)
    private String description;

    @Column(name = "finished_at")
    private OffsetDateTime finishedAt;

    @Column(length = 200, nullable = false)
    private String sort;

    @Column(nullable = false)
    private Boolean done;

    @Column(nullable = false)
    private Integer goal;

    private Integer progress;
}