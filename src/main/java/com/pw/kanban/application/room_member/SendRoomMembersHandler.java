package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SendRoomMembersHandler {
    private final SimpMessagingTemplate msgTemplate;
    private final GetAllRoomMembersHandler getAllRoomMembersHandler;

    public void handle(UUID roomId) {
        List<RoomMemberRepresentation> roomMemberRepresentationList = getAllRoomMembersHandler.handle(roomId);
        msgTemplate.convertAndSend("/topic/room/" + roomId +"/members", roomMemberRepresentationList);
    }
}
