package com.pw.kanban.domain.task;

import com.pw.kanban.domain.room_member.Color;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TaskDto {
    UUID taskId;
    UUID roomId;
    Boolean isBlocked;
    TaskType type;
    ColumnName kanbanColumn;
    Integer startDay;
    Integer endDay;
    Integer visibleFromDay;
    Double effort;
    String name;
    Integer dueDay;
    List<UUID> roomMembers;
    UUID editorId;
    Integer dayModified;
}
