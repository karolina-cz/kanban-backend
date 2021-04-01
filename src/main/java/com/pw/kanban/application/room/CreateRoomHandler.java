package com.pw.kanban.application.room;

import com.pw.kanban.application.day.DayGenerator;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomDto;
import com.pw.kanban.domain.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateRoomHandler {
    private final RoomRepository roomRepository;
    private final RoomDtoMapper roomDtoMapper;
    private final DayGenerator dayGenerator;

    @Transactional
    public UUID handle(RoomDto roomDto) {
        // TODO add generating days and special tasks
        Room room = roomDtoMapper.mapRoomDtoToRoom(roomDto);
        room = roomRepository.save(room);
        dayGenerator.generateDaysForRoom(room);
        return room.getRoomId();
    }

}
