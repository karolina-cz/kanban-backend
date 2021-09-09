package com.pw.kanban.domain.room_member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RoomMemberRepresentation {
    UUID roomMemberId;
    String name;
    Color color;
    boolean isActive;
    RoomMemberType type;
    Double[] dailyProductivity;
}
