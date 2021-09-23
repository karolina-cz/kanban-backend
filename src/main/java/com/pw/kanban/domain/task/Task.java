package com.pw.kanban.domain.task;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room_member.RoomMember;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

// TODO urgent add due date
@Data
@Entity
@Table(name="task")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private UUID taskId;

    @Column(name="name")
    private String name;

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

    @Column (name= "due_day")
    private Integer dueDay;

    @Column(name = "visible_from_day")
    private Integer visibleFromDay;

    @Column(name = "effort")
    private Double effort;

    @Column(name = "work_points1")
    private String workPoints1;

    @Column(name = "work_points2")
    private String workPoints2;

    @ManyToMany
    @JoinTable(
            name = "assignee",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "room_member_id"))
    private List<RoomMember> assignees;

    public Task(Room room, Boolean isBlocked, ColumnName kanbanColumn, Integer startDay, Integer endDay, Double effort,
                Integer visibleFromDay, TaskType type, String name, String workPoints1, String workPoints2, Integer dueDay) {
        this.room = room;
        this.isBlocked = isBlocked;
        this.kanbanColumn = kanbanColumn;
        this.startDay = startDay;
        this.endDay = endDay;
        this.effort = effort;
        this.visibleFromDay = visibleFromDay;
        this.type = type;
        this.name = name;
        this.workPoints1 = workPoints1;
        this.workPoints2 = workPoints2;
        this.dueDay = dueDay;
    }
}
