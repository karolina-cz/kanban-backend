package com.pw.kanban.domain.room;

import lombok.Data;

@Data
public class RoomDto {
    RoomType roomType;
    Integer blockersProbability;
}
