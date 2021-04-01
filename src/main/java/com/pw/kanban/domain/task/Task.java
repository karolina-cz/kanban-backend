package com.pw.kanban.domain.task;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.work_point.WorkPoint;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {
    //TODO update wielu taskow jednoczesnie - blokady

    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private UUID taskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="room_id")
    private Room room;

    @NonNull
    @Column(name="is_blocked")
    private boolean isBlocked;

    @NonNull
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @NonNull
    @Column(name="kanban_column")
    @Enumerated(EnumType.STRING)
    private ColumnName kanbanColumn;

    @Column(name = "start_day")
    private Integer startDay;

    @Column(name = "end_day")
    private Integer endDay;

    @Column(name = "visible_from_day")
    private Integer visibleFromDay;

    @Column(name = "effort")
    private Double effort;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    @ToString.Exclude
    private List<WorkPoint> workPoints;

    @ManyToMany
    @JoinTable(
            name = "assignee",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "room_member_id"))
    private Set<RoomMember> assignees;

    public Task(Room room, boolean isBlocked, ColumnName kanbanColumn, int startDay, int endDay, double effort,
                int visibleFromDay, TaskType type) {
        this.room = room;
        this.isBlocked = isBlocked;
        this.kanbanColumn = kanbanColumn;
        this.startDay = startDay;
        this.endDay = endDay;
        this.effort = effort;
        this.visibleFromDay = visibleFromDay;
        this.type = type;
    }
}
