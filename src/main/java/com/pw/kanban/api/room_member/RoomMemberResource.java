package com.pw.kanban.api.room_member;

import com.pw.kanban.application.room_member.CreateRoomMemberHandler;
import com.pw.kanban.application.room_member.DeleteRoomMemberHandler;
import com.pw.kanban.application.room_member.GetAllRoomMembersHandler;
import com.pw.kanban.application.room_member.PatchRoomMemberHandler;
import com.pw.kanban.domain.room_member.RoomMemberDto;
import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RoomMemberRepresentation createRoomMember(@RequestBody RoomMemberDto roomMemberDto) {
        return createRoomMemberHandler.handle(roomMemberDto);
    }

    @DeleteMapping("/{roomMemberId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteRoomMember(@PathVariable UUID roomMemberId) {
        deleteRoomMemberHandler.handle(roomMemberId);
    }

    @GetMapping("/room/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    List<RoomMemberRepresentation> getAllRoomMembersByRoomId(@PathVariable UUID roomId) {
        return getAllRoomMembersHandler.handle(roomId);
    }

    @PatchMapping("/{roomMemberId}")
    @ResponseStatus(HttpStatus.OK)
    RoomMemberRepresentation patchRoomMember(@PathVariable UUID roomMemberId, @RequestBody RoomMemberDto roomMemberDto) {
        return patchRoomMemberHandler.handle(roomMemberDto, roomMemberId);
    }
}
