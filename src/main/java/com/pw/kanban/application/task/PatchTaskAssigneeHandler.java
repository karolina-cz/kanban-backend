package com.pw.kanban.application.task;

import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberRepository;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatchTaskAssigneeHandler {
    private final TaskRepository taskRepository;
    private final RoomMemberRepository roomMemberRepository;

    @Transactional
    public void handle(UUID taskId, UUID roomMemberId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        RoomMember roomMember = roomMemberRepository.findById(roomMemberId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        task.getAssignees().add(roomMember);
        taskRepository.save(task);
    }
}
