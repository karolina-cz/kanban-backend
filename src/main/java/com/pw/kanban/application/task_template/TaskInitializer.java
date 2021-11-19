package com.pw.kanban.application.task_template;

import com.pw.kanban.application.work_point.GenerateWorkPointsHandler;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.simulation.SimulationRepository;
import com.pw.kanban.domain.task.*;
import com.pw.kanban.domain.task_template.TaskTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskInitializer {
    private final TaskRepository taskRepository;
    private final GenerateWorkPointsHandler generateWorkPointsHandler;
    private final SimulationRepository simulationRepository;

    @Transactional
    public void initializeTasks (Room room) {
        List<TaskTemplate> taskTemplates = simulationRepository
                .findBySimulationType(room.getType()).get(0).getTaskTemplates();
        taskTemplates.forEach(taskTemplate -> {
            for (int i = 0; i < taskTemplate.getTasksNumber(); i++) {
                String taskNamePrefix = taskTemplate.getTasksType() == TaskType.STANDARD ? "S." :
                        taskTemplate.getTasksType() == TaskType.FIXED_DATE ? "UD." : "P.";
                Task task = new Task(room, false, ColumnName.BACKLOG, null, null, null,
                        taskTemplate.getVisibleFromDay(), taskTemplate.getTasksType(), taskNamePrefix + (i + 1), taskTemplate.getDueDay());
                this.taskRepository.save(task);
                task.setWorkPoints(generateWorkPointsHandler.handle(task));
                this.taskRepository.save(task);
            }
        });
    }
}
