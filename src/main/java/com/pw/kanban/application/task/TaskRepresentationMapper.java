package com.pw.kanban.application.task;

import com.pw.kanban.application.assignee.AssigneeRepresentationMapper;
import com.pw.kanban.application.work_point.WorkPointRepresentationMapper;
import com.pw.kanban.domain.assignee.Assignee;
import com.pw.kanban.domain.assignee.AssigneeRepresentation;
import com.pw.kanban.domain.room_member.Color;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskRepresentation;
import com.pw.kanban.domain.work_point.WorkPointRepresentation;
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
    private final WorkPointRepresentationMapper workPointRepresentationMapper;

    @Transactional
    public TaskRepresentation mapTaskToRepresentation(Task task) {
        List<Assignee> assignees = task.getAssignees();
        List<AssigneeRepresentation> assigneeRepresentations = assigneeRepresentationMapper
                .mapAssigneesToRepresentation(assignees);
        List<WorkPointRepresentation> workPoints = workPointRepresentationMapper.mapWorkPointsToRepresentation(task.getWorkPoints());
        return new TaskRepresentation(task.getTaskId(),
                task.isBlocked(), task.getType(), task.getKanbanColumn(),
                task.getStartDay(), task.getEndDay(), task.getVisibleFromDay(), task.getEffort(), task.getName(),
                assigneeRepresentations, task.getDueDay(), workPoints);
    }

    public List<TaskRepresentation> mapTasksToRepresentation(List<Task> tasks){
        if (tasks == null) return Collections.emptyList();
        return tasks.stream().map(this::mapTaskToRepresentation).collect(Collectors.toList());
    }
}
