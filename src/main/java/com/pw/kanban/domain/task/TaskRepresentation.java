package com.pw.kanban.domain.task;

import com.pw.kanban.domain.room_member.Color;
import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TaskRepresentation {
    UUID taskId;
    List<Color> workPoints1;
    List<Color> workPoints2;
    Boolean isBlocked;
    TaskType type;
    ColumnName kanbanColumn;
    Integer startDay;
    Integer endDay;
    Integer visibleFromDay;
    Double effort;
    String name;
    List<RoomMemberRepresentation> roomMembers;
    Integer dueDay;
}
