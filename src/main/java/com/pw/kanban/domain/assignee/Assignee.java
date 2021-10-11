package com.pw.kanban.domain.assignee;

import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.work_point.WorkPoint;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="assignee")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Assignee {

    @Id
    @GeneratedValue
    @Column(name="assignee_id")
    private UUID assigneeId;

    @ManyToOne
    @JoinColumn(name="room_member_id")
    @ToString.Exclude
    private RoomMember roomMember;

    @ManyToOne
    @JoinColumn(name="task_id")
    @ToString.Exclude
    private Task task;

    @Column(name = "assignee_type")
    @Enumerated(EnumType.STRING)
    private AssigneeType assigneeType;

    @OneToMany(mappedBy = "assignee")
    @ToString.Exclude
    private List<WorkPoint> workPoints;

    public Assignee(RoomMember roomMember, Task task, AssigneeType assigneeType) {
        this.roomMember = roomMember;
        this.task = task;
        this.assigneeType = assigneeType;
    }

}
