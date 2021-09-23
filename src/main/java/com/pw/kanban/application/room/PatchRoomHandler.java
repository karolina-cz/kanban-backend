package com.pw.kanban.application.room;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomDto;
import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.room.RoomRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatchRoomHandler {
    private final RoomRepository roomRepository;
    private final RoomRepresentationMapper roomRepresentationMapper;

    @Transactional
    public RoomRepresentation handle(RoomDto roomDto, UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (roomDto.getStageOneLimit() != null) room.setStageOneLimit(roomDto.getStageOneLimit());
        if(roomDto.getStageOneInProgressLimit() != null) room.setStageOneInProgressLimit(roomDto.getStageOneInProgressLimit());
        if(roomDto.getStageOneCommittedLimit() != null) room.setStageOneCommittedLimit(roomDto.getStageOneCommittedLimit());
        if(roomDto.getStageOneDoneLimit() != null) room.setStageOneDoneLimit(roomDto.getStageOneDoneLimit());
        if(roomDto.getStageTwoLimit() != null) room.setStageTwoLimit(roomDto.getStageTwoLimit());
        if(roomDto.getDoneLimit() != null) room.setDoneLimit(roomDto.getDoneLimit());
        if(roomDto.getBlockersProbability() != null) room.setBlockersProbability(roomDto.getBlockersProbability());
        roomRepository.save(room);
        return roomRepresentationMapper.mapRoomToRepresentation(room);
    }
}
