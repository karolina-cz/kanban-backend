package com.pw.kanban.domain.task;

import com.pw.kanban.domain.work_point.WorkPointRepresentation;

import java.util.Collection;

public class TaskRepresentation {
    Collection<WorkPointRepresentation> workPoints;
    boolean isBlocked;
    TaskType type;
    ColumnName kanbanColumn;
    int startDay;
    int endDay;
    int visibleFromDay;
    double effort;
}

