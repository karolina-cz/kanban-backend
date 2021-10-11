package com.pw.kanban.application.assignee;

import com.pw.kanban.domain.assignee.Assignee;
import com.pw.kanban.domain.assignee.AssigneeRepository;
import com.pw.kanban.domain.assignee.AssigneeType;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberRepository;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskRepository;
import com.pw.kanban.domain.work_point.WorkPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatchTaskAssigneeHandler {
    private final TaskRepository taskRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final AssigneeRepository assigneeRepository;
    private final WorkPointRepository workPointRepository;

    @Transactional
    public void handle(UUID taskId, UUID roomMemberId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        task.getAssignees().forEach(assignee -> {
            assignee.getWorkPoints().forEach(workPoint -> {
                workPoint.setAssignee(null);
                workPointRepository.save(workPoint);
            });
            assignee.getWorkPoints().clear();
            assigneeRepository.save(assignee);
            this.assigneeRepository.deleteById(assignee.getAssigneeId());
        });
        task.getAssignees().clear();
        RoomMember roomMember = roomMemberRepository.findById(roomMemberId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        Assignee assignee = new Assignee(roomMember, task, AssigneeType.MAIN);
        task.getAssignees().add(assignee);
        taskRepository.save(task);
    }
}
