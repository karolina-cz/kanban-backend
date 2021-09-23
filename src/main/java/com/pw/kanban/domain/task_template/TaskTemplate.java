package com.pw.kanban.domain.task_template;

import com.pw.kanban.domain.simulation.Simulation;
import com.pw.kanban.domain.task.TaskType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="task_template")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskTemplate {

    @Id
    @GeneratedValue
    @Column(name = "task_template_id")
    private UUID taskTemplateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="simulation_id")
    private Simulation simulation;

    @NonNull
    @Column(name = "tasks_number")
    private int tasksNumber;

    @NonNull
    @Column(name = "tasks_type")
    @Enumerated(EnumType.STRING)
    private TaskType tasksType;

    @Column(name = "visible_from_day")
    private Integer visibleFromDay;

    @Column(name = "due_day")
    private Integer dueDay;

    public TaskTemplate(Simulation simulation, int tasksNumber, TaskType tasksType, Integer visibleFromDay,
                        Integer dueDay) {
        this.simulation = simulation;
        this.tasksNumber = tasksNumber;
        this.tasksType = tasksType;
        this.visibleFromDay = visibleFromDay;
        this.dueDay = dueDay;
    }
}
