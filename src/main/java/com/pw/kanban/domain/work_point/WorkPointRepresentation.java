package com.pw.kanban.domain.work_point;

import com.pw.kanban.domain.room_member.Color;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class WorkPointRepresentation {
    UUID workPointId;
    Color color;
    Integer stage;
    Integer pointIndex;
}
