package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberDto;
import com.pw.kanban.domain.room_member.RoomMemberRepository;
import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateRoomMemberHandler {
    private final RoomMemberDtoMapper roomMemberDtoMapper;
    private final RoomMemberRepresentationMapper roomMemberRepresentationMapper;
    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;

    @Transactional
    public RoomMemberRepresentation handle(RoomMemberDto roomMemberDto) {
        Room room = roomRepository.getOne(roomMemberDto.getRoomId());
        RoomMember roomMember = roomMemberDtoMapper.mapRoomMemberDtoToRoomMember(roomMemberDto);
        roomMemberRepository.save(roomMember);
        room.getRoomMembers().add(roomMember);
        roomRepository.save(room);
        return roomMemberRepresentationMapper.mapRoomMemberToRepresentation(roomMember);
    }
}
