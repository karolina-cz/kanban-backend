package com.pw.kanban.application.work_point;


import com.pw.kanban.domain.work_point.WorkPoint;
import com.pw.kanban.domain.work_point.WorkPointRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkPointRepresentationMapper {

    public WorkPointRepresentation mapWorkPointToRepresentation(WorkPoint workPoint) {
        if (workPoint.getAssignee() != null && workPoint.getAssignee().getRoomMember() != null) {
            return new WorkPointRepresentation(workPoint.getWorkPointId(),
                    workPoint.getAssignee().getRoomMember().getColor(), workPoint.getStage(), workPoint.getPointIndex());
        }
        return new WorkPointRepresentation(workPoint.getWorkPointId(), null, workPoint.getStage(), workPoint.getPointIndex());
    }

    public List<WorkPointRepresentation> mapWorkPointsToRepresentation(List<WorkPoint> workPoints) {
        if (workPoints == null) return Collections.emptyList();
        return workPoints.stream().map(this::mapWorkPointToRepresentation).collect(Collectors.toList());
    }
}
