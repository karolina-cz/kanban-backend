package com.pw.kanban.application.room;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.room.RoomRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetRoomHandler {

    private final RoomRepository roomRepository;
    private final RoomRepresentationMapper roomRepresentationMapper;

    @Transactional
    public RoomRepresentation handle (UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return roomRepresentationMapper.mapRoomToRepresentation(room);
    }

}
