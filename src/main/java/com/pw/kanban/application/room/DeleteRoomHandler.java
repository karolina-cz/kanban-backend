package com.pw.kanban.application.room;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteRoomHandler {

    private final RoomRepository roomRepository;

    @Transactional
    public void handle(UUID roomId) {
        //TODO add deleting all tasks, room members, days connected to room
        // when deleting tasks - delete work points
        // when deleting members - delte work points (if are any)
        // when deleting day - delete daily productivity
        roomRepository.deleteById(roomId);
    }
}
