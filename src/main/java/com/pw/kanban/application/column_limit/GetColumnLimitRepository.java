package com.pw.kanban.application.column_limit;

import com.pw.kanban.domain.column_limit.ColumnLimit;
import com.pw.kanban.domain.column_limit.ColumnLimitRepository;
import com.pw.kanban.domain.column_limit.ColumnLimitRepresentation;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetColumnLimitRepository {
    private final RoomRepository roomRepository;
    private final ColumnLimitRepository columnLimitRepository;
    private final ColumnLimitRepresentationMapper columnLimitRepresentationMapper;

    @Transactional
    public List<ColumnLimitRepresentation> getAllColumnLimitsByRoomId(UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<ColumnLimit> columnLimits = room.getColumnLimits();
        return columnLimitRepresentationMapper.mapColumnLimitsToRepresentation(columnLimits);
    }

    @Transactional
    public UUID getColumnLimitRoomId(UUID columnLimitId) {
        ColumnLimit columnLimit = columnLimitRepository.findById(columnLimitId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return columnLimit.getRoom().getRoomId();
    }
}
