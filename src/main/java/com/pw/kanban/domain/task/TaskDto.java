package com.pw.kanban.domain.task;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskDto {
    UUID roomId;
    boolean isBlocked;
    TaskType type;
    ColumnName kanbanColumn;
    int startDay;
    Integer endDay;
    Integer visibleFromDay;
    double effort;
}
