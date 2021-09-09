package com.pw.kanban.application.task;

import com.pw.kanban.application.room_member.RoomMemberRepresentationMapper;
import com.pw.kanban.domain.room_member.Color;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskRepresentationMapper {
    private final RoomMemberRepresentationMapper roomMemberRepresentationMapper;
    private final WorkPointConverter workPointConverter;

    @Transactional
    public TaskRepresentation mapTaskToRepresentation(Task task) {
        List<Color> workPoints1 = workPointConverter.stringToColorArray(task.getWorkPoints1());
        List<Color> workPoints2 = workPointConverter.stringToColorArray(task.getWorkPoints2());
        List<RoomMember> assignees = task.getAssignees();
        List<RoomMemberRepresentation> roomMemberRepresentations = roomMemberRepresentationMapper
                .mapRoomMembersToRepresentation(assignees);
        return new TaskRepresentation(task.getTaskId(), workPoints1, workPoints2,
                task.isBlocked(), task.getType(), task.getKanbanColumn(),
                task.getStartDay(), task.getEndDay(), task.getVisibleFromDay(), task.getEffort(), task.getName(),
                roomMemberRepresentations, task.getDueDay());
    }

    public List<TaskRepresentation> mapTasksToRepresentation(List<Task> tasks){
        //TODO add returning empty list if null
        return tasks.stream().map(this::mapTaskToRepresentation).collect(Collectors.toList());
    }
}
