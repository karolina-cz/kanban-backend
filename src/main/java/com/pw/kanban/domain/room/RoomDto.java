package com.pw.kanban.domain.room;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomDto {
    RoomType roomType;
    Integer blockersProbability;
    Integer currentDay;
}
