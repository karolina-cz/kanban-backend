package com.pw.kanban.application.room;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RoomDtoMapper {

    public Room mapRoomDtoToRoom(RoomDto roomDto) {
        //TODO create constants class with initial values for room limits
        return new Room(roomDto.getRoomType(), 4, 4, 4,
                4, 4, 4);
    }
}
