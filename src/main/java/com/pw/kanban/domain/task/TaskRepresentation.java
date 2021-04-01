package com.pw.kanban.domain.task;

import com.pw.kanban.domain.work_point.WorkPointRepresentation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class TaskRepresentation {
    Collection<WorkPointRepresentation> workPoints;
    Boolean isBlocked;
    TaskType type;
    ColumnName kanbanColumn;
    Integer startDay;
    Integer endDay;
    Integer visibleFromDay;
    Double effort;
    String name;
}

