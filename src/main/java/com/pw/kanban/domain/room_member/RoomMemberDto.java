package com.pw.kanban.domain.room_member;

import lombok.Data;

import java.util.UUID;

@Data
public class RoomMemberDto {
    UUID roomId;
    String name;
    Boolean isActive;
}
