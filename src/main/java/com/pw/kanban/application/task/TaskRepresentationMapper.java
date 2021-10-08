package com.pw.kanban.application.task;

import com.pw.kanban.application.assignee.AssigneeRepresentationMapper;
import com.pw.kanban.domain.assignee.Assignee;
import com.pw.kanban.domain.assignee.AssigneeRepresentation;
import com.pw.kanban.domain.room_member.Color;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskRepresentationMapper {
    private final AssigneeRepresentationMapper assigneeRepresentationMapper;
    private final WorkPointConverter workPointConverter;

    @Transactional
    public TaskRepresentation mapTaskToRepresentation(Task task) {
        List<Color> workPoints1 = workPointConverter.stringToColorArray(task.getWorkPoints1());
        List<Color> workPoints2 = workPointConverter.stringToColorArray(task.getWorkPoints2());
        List<Assignee> assignees = task.getAssignees();
        List<AssigneeRepresentation> assigneeRepresentations = assigneeRepresentationMapper
                .mapAssigneesToRepresentation(assignees);
        return new TaskRepresentation(task.getTaskId(), workPoints1, workPoints2,
                task.isBlocked(), task.getType(), task.getKanbanColumn(),
                task.getStartDay(), task.getEndDay(), task.getVisibleFromDay(), task.getEffort(), task.getName(),
                assigneeRepresentations, task.getDueDay());
    }

    public List<TaskRepresentation> mapTasksToRepresentation(List<Task> tasks){
        if (tasks == null) return Collections.emptyList();
        return tasks.stream().map(this::mapTaskToRepresentation).collect(Collectors.toList());
    }
}
