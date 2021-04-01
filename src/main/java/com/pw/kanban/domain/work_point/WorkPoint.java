package com.pw.kanban.domain.work_point;


import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.task.Task;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="work_point")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkPoint {

    @Id
    @GeneratedValue
    @Column(name = "work_point_id")
    private UUID workPointId;

    @NonNull
    @Column(name = "point_index")
    private int pointIndex;

    @NonNull
    @Column(name = "stage")
    private int stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="room_member_id")
    private RoomMember roomMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="task_id")
    private Task task;


    public WorkPoint(int pointIndex, int stage, RoomMember roomMember, Task task) {
        this.pointIndex = pointIndex;
        this.stage = stage;
        this.roomMember = roomMember;
        this.task = task;
    }
}
