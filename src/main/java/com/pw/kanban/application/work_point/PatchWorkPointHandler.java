package com.pw.kanban.application.work_point;

import com.pw.kanban.domain.assignee.Assignee;
import com.pw.kanban.domain.assignee.AssigneeRepository;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.work_point.WorkPoint;
import com.pw.kanban.domain.work_point.WorkPointDto;
import com.pw.kanban.domain.work_point.WorkPointRepository;
import com.pw.kanban.domain.work_point.WorkPointRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatchWorkPointHandler {
    private final WorkPointRepository workPointRepository;
    private final AssigneeRepository assigneeRepository;
    private final WorkPointRepresentationMapper workPointRepresentationMapper;

    @Transactional
    public WorkPointRepresentation handle(WorkPointDto workPointDto, UUID workPointId) {
        WorkPoint workPoint = workPointRepository.findById(workPointId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (workPointDto.getDayModified() != null) workPoint.setDayModified(workPointDto.getDayModified());
        if (workPointDto.getAssigneeId() == null) {
            workPoint.setAssignee(null);
        } else {
            Assignee assignee = assigneeRepository.findById(workPointDto.getAssigneeId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND));
            workPoint.setAssignee(assignee);
        }
        workPointRepository.save(workPoint);
        return workPointRepresentationMapper.mapWorkPointToRepresentation(workPoint);
    }
}
