package com.pw.kanban.domain.work_point;

import com.pw.kanban.domain.assignee.Assignee;
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
    @Column(name="work_point_id")
    private UUID workPointId;

    @Column(name="point_index")
    @NonNull
    private Integer pointIndex;

    @Column(name = "stage")
    @NonNull
    private Integer stage;

    @Column(name = "day_modified")
    private Integer dayModified;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name="assignee_id")
    private Assignee assignee;

    public WorkPoint(@NonNull Integer pointIndex, @NonNull Integer stage, Integer dayModified, Task task, Assignee assignee) {
        this.pointIndex = pointIndex;
        this.stage = stage;
        this.dayModified = dayModified;
        this.task = task;
        this.assignee = assignee;
    }


}
