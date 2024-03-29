package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomMemberRepresentationMapper {
    private final MemberProductivityConverter memberProductivityConverter;
    private final RoomMemberProductivityComputer roomMemberProductivityComputer;

    public RoomMemberRepresentation mapRoomMemberToRepresentation(RoomMember roomMember) {
        if(roomMember == null) return null;
        Double[] dailyProductivity = memberProductivityConverter.stringToDoubleArray(roomMember.getDailyProductivity());
        Double[] usedProductivity = roomMemberProductivityComputer.compute(roomMember);
        return new RoomMemberRepresentation(roomMember.getRoomMemberId(), roomMember.getName(), roomMember.getColor(), roomMember.getType(), dailyProductivity, usedProductivity);
    }

    public List<RoomMemberRepresentation> mapRoomMembersToRepresentation(List<RoomMember> roomMembers) {
        if(roomMembers == null) return Collections.emptyList();
        return roomMembers.stream()
                .map(this::mapRoomMemberToRepresentation)
                .collect(Collectors.toList());
    }
}
