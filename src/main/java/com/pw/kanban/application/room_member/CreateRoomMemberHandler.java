package com.pw.kanban.application.room_member;

import com.pw.kanban.application.daily_productivity.DailyProductivityGenerator;
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

    private final RoomMemberRepository roomMemberRepository;
    private final RoomMemberDtoMapper roomMemberDtoMapper;
    private final RoomMemberRepresentationMapper roomMemberRepresentationMapper;
    private final DailyProductivityGenerator dailyProductivityGenerator;

    @Transactional
    public RoomMemberRepresentation handle(RoomMemberDto roomMemberDto) {
        //TODO if room member is viewer - color is always the same - for example black
        RoomMember roomMember = roomMemberDtoMapper.mapRoomMemberDtoToRoomMember(roomMemberDto);
        roomMember = roomMemberRepository.save(roomMember);
        dailyProductivityGenerator.generateDailyProductivityForRoomMember(roomMember);
        return roomMemberRepresentationMapper.mapRoomMemberToRepresentation(roomMember);
    }
}
