package com.pw.kanban.application.room;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomDtoMapper {

    public Room mapRoomDtoToRoom(RoomDto roomDto) {
        return new Room(roomDto.getRoomType(), 25);
    }
}
