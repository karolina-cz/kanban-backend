package com.pw.kanban.application.task_template;

import com.pw.kanban.application.task.WorkPointConverter;
import com.pw.kanban.application.work_point.GenerateWorkPointsHandler;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.task.*;
import com.pw.kanban.domain.task_template.TaskTemplate;
import com.pw.kanban.domain.task_template.TaskTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pw.kanban.application.task.GenerateTaskHandler.POINTS_PER_STAGE;

@Service
@RequiredArgsConstructor
public class TaskInitializer {
    private final TaskTemplateRepository taskTemplateRepository;
    private final TaskRepository taskRepository;
    private final WorkPointConverter workPointConverter;
    private final GenerateWorkPointsHandler generateWorkPointsHandler;

    @Transactional
    public void initializeTasks (Room room) {
        List<TaskTemplate> taskTemplates = taskTemplateRepository.findAll();
        taskTemplates.forEach(taskTemplate -> {
            for (int i = 0; i < taskTemplate.getTasksNumber(); i++) {
                String taskNamePrefix = taskTemplate.getTasksType() == TaskType.STANDARD ? "S." :
                        taskTemplate.getTasksType() == TaskType.FIXED_DATE ? "UD." : "P.";
                String[] workPoints1 = new String[POINTS_PER_STAGE];
                String workPoints = workPointConverter.stringArrayToString(workPoints1);
                Task task = new Task(room, false, ColumnName.BACKLOG, null, null, null,
                        taskTemplate.getVisibleFromDay(), taskTemplate.getTasksType(), taskNamePrefix + (i + 1), workPoints, workPoints, taskTemplate.getDueDay());
                this.taskRepository.save(task);
                task.setWorkPoints(generateWorkPointsHandler.handle(task));
                this.taskRepository.save(task);
            }
        });
    }
}
