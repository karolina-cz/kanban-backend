package com.pw.kanban.api.room_member;

import com.pw.kanban.application.room_member.*;
import com.pw.kanban.domain.room_member.RoomMemberDto;
import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/roomMember")
@RequiredArgsConstructor
public class RoomMemberResource {
    private final CreateRoomMemberHandler createRoomMemberHandler;
    private final DeleteRoomMemberHandler deleteRoomMemberHandler;
    private final GetAllRoomMembersHandler getAllRoomMembersHandler;
    private final PatchRoomMemberHandler patchRoomMemberHandler;
    private final GetRoomMemberRoomHandler getRoomMemberRoomHandler;
    private final SimpMessagingTemplate msgTemplate;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RoomMemberRepresentation createRoomMember(@RequestBody RoomMemberDto roomMemberDto) {
        RoomMemberRepresentation roomMemberRepresentation = createRoomMemberHandler.handle(roomMemberDto);
        UUID roomId = roomMemberDto.getRoomId();
        sendUpdatedRoomMembers(roomId);
        return roomMemberRepresentation;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{roomMemberId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteRoomMember(@PathVariable UUID roomMemberId) {
        UUID roomId = getRoomMemberRoomHandler.handle(roomMemberId);
        deleteRoomMemberHandler.handle(roomMemberId);
        sendUpdatedRoomMembers(roomId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/room/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    List<RoomMemberRepresentation> getAllRoomMembersByRoomId(@PathVariable UUID roomId) {
        return getAllRoomMembersHandler.handle(roomId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/{roomMemberId}")
    @ResponseStatus(HttpStatus.OK)
    RoomMemberRepresentation patchRoomMember(@PathVariable UUID roomMemberId, @RequestBody RoomMemberDto roomMemberDto) {
        UUID roomId = getRoomMemberRoomHandler.handle(roomMemberId);
        RoomMemberRepresentation roomMemberRepresentation = patchRoomMemberHandler.handle(roomMemberDto, roomMemberId);
        sendUpdatedRoomMembers(roomId);
        return roomMemberRepresentation;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    void patchRoomMembers(@RequestBody List<RoomMemberDto> roomMemberDtos) {
        if(roomMemberDtos.size() == 0) return;
        UUID roomId = getRoomMemberRoomHandler.handle(roomMemberDtos.get(0).getRoomMemberId());
        patchRoomMemberHandler.handleMultiple(roomMemberDtos);
        sendUpdatedRoomMembers(roomId);
    }

    private void sendUpdatedRoomMembers(UUID roomId) {
        List<RoomMemberRepresentation> roomMemberRepresentationList = getAllRoomMembersHandler.handle(roomId);
        msgTemplate.convertAndSend("/topic/room/" + roomId +"/members", roomMemberRepresentationList);
    }
}
