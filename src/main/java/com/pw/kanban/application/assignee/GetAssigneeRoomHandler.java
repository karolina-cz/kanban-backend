package com.pw.kanban.application.assignee;

import com.pw.kanban.domain.assignee.Assignee;
import com.pw.kanban.domain.assignee.AssigneeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetAssigneeRoomHandler {
    private final AssigneeRepository assigneeRepository;

    @Transactional
    public UUID handle(UUID assigneeId) {
        Assignee assignee= assigneeRepository.findById(assigneeId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return assignee.getTask().getRoom().getRoomId();
    }
}
