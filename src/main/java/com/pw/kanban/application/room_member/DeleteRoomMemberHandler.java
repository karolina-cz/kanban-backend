package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room_member.RoomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteRoomMemberHandler {
    private final RoomMemberRepository roomMemberRepository;

    public void handle(UUID roomMemberId) {
        // TODO delete all associated work points and daily productivity
        roomMemberRepository.deleteById(roomMemberId);
    }
}
