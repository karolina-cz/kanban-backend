package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAllRoomMembersHandler {
    private final RoomRepository roomRepository;
    private final RoomMemberRepresentationMapper roomMemberRepresentationMapper;

    @Transactional
    public List<RoomMemberRepresentation> handle(UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<RoomMember> roomMembers = room.getRoomMembers();
        return (List<RoomMemberRepresentation>) roomMemberRepresentationMapper.mapRoomMembersToRepresentation(roomMembers);
    }
}
