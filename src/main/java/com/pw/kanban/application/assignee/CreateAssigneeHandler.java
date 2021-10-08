package com.pw.kanban.application.assignee;

import com.pw.kanban.domain.assignee.*;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberRepository;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateAssigneeHandler {
    private final TaskRepository taskRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final AssigneeRepresentationMapper assigneeRepresentationMapper;
    private final AssigneeRepository assigneeRepository;

    @Transactional
    public AssigneeRepresentation handle(AssigneeDto assigneeDto) {
        Task task = taskRepository.findById(assigneeDto.getTaskId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        RoomMember roomMember = roomMemberRepository.findById(assigneeDto.getRoomMemberId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if (this.isHelperAndDuplicate(assigneeDto, task, roomMember)) {
            return null;
        }
        if (assigneeDto.getAssigneeType() == AssigneeType.MAIN) {
            this.prepareListForMainAssignee(task, assigneeDto);
        }
        Assignee assignee = new Assignee(roomMember, task, assigneeDto.getAssigneeType());
        task.getAssignees().add(assignee);
        taskRepository.save(task);
        return this.assigneeRepresentationMapper.mapAssigneeToRepresentation(assignee);
    }

    private boolean isHelperAndDuplicate(AssigneeDto assigneeDto, Task task, RoomMember roomMember) {
        return assigneeDto.getAssigneeType() == AssigneeType.HELPER && containsRoomMemberId(task.getAssignees().stream()
                .map(Assignee::getRoomMember).collect(Collectors.toList()), roomMember.getRoomMemberId());
    }

    private boolean containsRoomMemberId(final List<RoomMember> list, final UUID roomMemberId){
        return list.stream().anyMatch(o -> o.getRoomMemberId().equals(roomMemberId));
    }

    private void prepareListForMainAssignee(Task task, AssigneeDto assigneeDto) {
        task.getAssignees().stream()
                .filter(obj -> obj.getAssigneeType() == AssigneeType.MAIN ||
                        obj.getRoomMember().getRoomMemberId() == assigneeDto.getRoomMemberId())
                .collect(Collectors.toList())
                .forEach(obj -> this.assigneeRepository.deleteById(obj.getAssigneeId()));
        task.getAssignees().removeIf(obj -> obj.getAssigneeType() == AssigneeType.MAIN ||
                obj.getRoomMember().getRoomMemberId() == assigneeDto.getRoomMemberId());
    }
}
