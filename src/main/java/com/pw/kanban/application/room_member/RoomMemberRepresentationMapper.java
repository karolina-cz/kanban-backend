package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomMemberRepresentationMapper {

    public RoomMemberRepresentation mapRoomMemberToRepresentation(RoomMember roomMember) {
        return new RoomMemberRepresentation(roomMember.getRoomMemberId(), roomMember.getName(), roomMember.getColor(),
                roomMember.isActive());
    }

    public List<RoomMemberRepresentation> mapRoomMembersToRepresentation(List<RoomMember> roomMembers) {
        return roomMembers.stream()
                .map(this::mapRoomMemberToRepresentation)
                .collect(Collectors.toList());
    }
}
