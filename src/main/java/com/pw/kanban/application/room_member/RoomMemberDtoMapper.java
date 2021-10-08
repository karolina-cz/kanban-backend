package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.room.RoomType;
import com.pw.kanban.domain.room_member.Color;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberDto;
import com.pw.kanban.domain.room_member.RoomMemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomMemberDtoMapper {
    public static final Integer DAYS_COUNT = 10;
    private final RoomRepository roomRepository;
    private final RoomMemberColorGenerator colorGenerator;
    private final MemberProductivityConverter memberProductivityConverter;

    @Transactional
    public RoomMember mapRoomMemberDtoToRoomMember(RoomMemberDto roomMemberDto) {
        Room room = roomRepository.findById(roomMemberDto.getRoomId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        Color color = Color.BLACK;
        if(roomMemberDto.getType() != RoomMemberType.VIEWER &&
            !(room.getType() == RoomType.KANBAN_BOARD && room.getRoomMembers().size() > 6)) {
            color = colorGenerator.generate(room.getRoomMembers());
        }
        RoomMemberType memberType = color == Color.BLACK ? RoomMemberType.VIEWER : RoomMemberType.PARTICIPANT;
        String productivity = memberProductivityConverter.doubleArrayToString(new Double[DAYS_COUNT]);
        return new RoomMember(roomMemberDto.getName().substring(0, Math.min(roomMemberDto.getName().length(), 30)), true,  color, room, memberType, productivity);
    }


}
