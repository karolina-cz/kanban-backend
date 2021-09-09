package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetRoomMemberRoomHandler {
    private final RoomMemberRepository roomMemberRepository;

    @Transactional
    public UUID handle(UUID roomMemberId) {
        RoomMember roomMember= roomMemberRepository.findById(roomMemberId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return roomMember.getRoom().getRoomId();
    }
}
