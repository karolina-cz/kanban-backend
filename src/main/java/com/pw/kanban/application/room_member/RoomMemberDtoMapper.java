package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.room_member.Color;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RoomMemberDtoMapper {
    private final RoomRepository roomRepository;

    @Transactional
    public RoomMember mapRoomMemberDtoToRoomMember(RoomMemberDto roomMemberDto) {
        Room room = roomRepository.findById(roomMemberDto.getRoomId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        Color color = Color.BLACK; // TODO add color generation
        return new RoomMember(roomMemberDto.getName(), true,  color, room);
    }
}
