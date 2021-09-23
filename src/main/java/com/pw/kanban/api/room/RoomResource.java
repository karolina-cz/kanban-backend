package com.pw.kanban.api.room;

import com.pw.kanban.application.room.CreateRoomHandler;
import com.pw.kanban.application.room.DeleteRoomHandler;
import com.pw.kanban.application.room.GetRoomHandler;
import com.pw.kanban.application.room.PatchRoomHandler;
import com.pw.kanban.domain.room.RoomDto;
import com.pw.kanban.domain.room.RoomRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/room")
@RequiredArgsConstructor
public class RoomResource {
    private final CreateRoomHandler createRoomHandler;
    private final GetRoomHandler getRoomHandler;
    private final DeleteRoomHandler deleteRoomHandler;
    private final PatchRoomHandler patchRoomHandler;
    private final SimpMessagingTemplate msgTemplate;


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UUID createRoom(@RequestBody RoomDto roomDto) {
        return createRoomHandler.handle(roomDto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path="/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    RoomRepresentation getRoom(@PathVariable UUID roomId) {
        return getRoomHandler.handle(roomId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path="/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteRoom(@PathVariable UUID roomId) {
        deleteRoomHandler.handle(roomId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping(path="/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    RoomRepresentation patchRoom(@RequestBody RoomDto roomDto, @PathVariable UUID roomId) {
        RoomRepresentation roomRepresentation = patchRoomHandler.handle(roomDto, roomId);
        this.sendUpdatedRoom(roomRepresentation);
        return roomRepresentation;
    }

    public void sendUpdatedRoom(RoomRepresentation roomRepresentation) {
        msgTemplate.convertAndSend("/topic/room/" + roomRepresentation.getRoomId() +"/info", roomRepresentation);
    }
}
