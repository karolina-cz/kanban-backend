package com.pw.kanban.domain.room;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RoomRepresentation {
    UUID roomId;
    RoomType type;
    Integer blockersProbability;
}
