package com.pw.kanban.application.assignee;


import com.pw.kanban.domain.assignee.AssigneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAssigneeHandler {
    private final AssigneeRepository assigneeRepository;

    @Transactional
    public void handle(UUID assigneeId) {
        this.assigneeRepository.deleteById(assigneeId);
    }

}
