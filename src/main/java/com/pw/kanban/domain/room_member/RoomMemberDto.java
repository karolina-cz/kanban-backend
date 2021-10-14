package com.pw.kanban.domain.room_member;

import lombok.Data;

import java.util.UUID;

@Data
public class RoomMemberDto {
    UUID roomMemberId;
    UUID roomId;
    String name;
    RoomMemberType type;
    Double[] dailyProductivity;
}
