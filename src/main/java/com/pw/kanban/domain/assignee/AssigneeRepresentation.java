package com.pw.kanban.domain.assignee;

import com.pw.kanban.domain.room_member.Color;
import com.pw.kanban.domain.room_member.RoomMemberType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AssigneeRepresentation {
    UUID assigneeId;
    UUID roomMemberId;
    String name;
    Color color;
    boolean isActive;
    RoomMemberType type;
    Double[] dailyProductivity;
    AssigneeType assigneeType;
}
