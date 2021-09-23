package com.pw.kanban.application.room;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepresentation;
import org.springframework.stereotype.Service;

@Service
public class RoomRepresentationMapper {

    public RoomRepresentation mapRoomToRepresentation(Room room) {
        return new RoomRepresentation(room.getRoomId(), room.getType(), room.getStageOneLimit(),
                room.getStageOneInProgressLimit(), room.getStageOneCommittedLimit(),
                room.getStageOneDoneLimit(), room.getStageTwoLimit(), room.getDoneLimit(), room.getBlockersProbability());
    }
}
