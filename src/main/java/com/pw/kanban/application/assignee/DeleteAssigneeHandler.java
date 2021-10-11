package com.pw.kanban.application.assignee;


import com.pw.kanban.domain.assignee.Assignee;
import com.pw.kanban.domain.assignee.AssigneeRepository;
import com.pw.kanban.domain.work_point.WorkPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAssigneeHandler {
    private final AssigneeRepository assigneeRepository;
    private final WorkPointRepository workPointRepository;

    @Transactional
    public void handle(UUID assigneeId) {
        Assignee assignee = assigneeRepository.findById(assigneeId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        assignee.getWorkPoints().forEach(workPoint -> {
            workPoint.setAssignee(null);
            workPointRepository.save(workPoint);
        });
        assignee.getWorkPoints().clear();
        assigneeRepository.save(assignee);
        this.assigneeRepository.deleteById(assigneeId);

    }

}
