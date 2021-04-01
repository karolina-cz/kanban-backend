package com.pw.kanban.application.task;

import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskRepresentationMapper {

    public TaskRepresentation mapTaskToRepresentation(Task task) {
        //TODO add returning work points with task
        return new TaskRepresentation(null, task.isBlocked(), task.getType(), task.getKanbanColumn(),
                task.getStartDay(), task.getEndDay(), task.getVisibleFromDay(), task.getEffort(), task.getName());
    }
}
