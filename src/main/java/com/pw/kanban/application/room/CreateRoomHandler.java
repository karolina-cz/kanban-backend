package com.pw.kanban.application.room;

import com.pw.kanban.application.column_limit.ColumnLimitGenerator;
import com.pw.kanban.application.task_template.TaskInitializer;
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
    private final TaskInitializer taskInitializer;
    private final ColumnLimitGenerator columnLimitGenerator;

    @Transactional
    public UUID handle(RoomDto roomDto) {
        Room room = roomDtoMapper.mapRoomDtoToRoom(roomDto);
        room = roomRepository.save(room);
        taskInitializer.initializeTasks(room);
        columnLimitGenerator.generateColumnLimits(room);
        return room.getRoomId();
    }

}
