package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room.RoomRepresentation;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberDto;
import com.pw.kanban.domain.room_member.RoomMemberRepository;
import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatchRoomMemberHandler {
    private final RoomMemberRepository roomMemberRepository;
    private final RoomMemberRepresentationMapper roomMemberRepresentationMapper;

    @Transactional
    public RoomMemberRepresentation handle(RoomMemberDto roomMemberDto, UUID roomMemberId) {
        RoomMember roomMember = roomMemberRepository.findById(roomMemberId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(roomMemberDto.getIsActive() != null) roomMember.setActive(roomMemberDto.getIsActive());
        if(roomMemberDto.getName() != null) roomMember.setName(roomMemberDto.getName());
        roomMemberRepository.save(roomMember);
        return roomMemberRepresentationMapper.mapRoomMemberToRepresentation(roomMember);
    }
}
