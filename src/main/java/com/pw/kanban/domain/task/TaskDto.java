package com.pw.kanban.domain.task;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskDto {
    UUID roomId;
    Boolean isBlocked;
    TaskType type;
    ColumnName kanbanColumn;
    Integer startDay;
    Integer endDay;
    Integer visibleFromDay;
    Double effort;
    String name;
}
