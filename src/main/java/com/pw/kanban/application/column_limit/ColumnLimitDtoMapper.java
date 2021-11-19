package com.pw.kanban.application.column_limit;

import com.pw.kanban.domain.column_limit.ColumnLimit;
import com.pw.kanban.domain.column_limit.ColumnLimitDto;
import com.pw.kanban.domain.column_limit.ColumnLimitType;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ColumnLimitDtoMapper {
    private final RoomRepository roomRepository;
    private final ColumnNameConverter columnNameConverter;

    @Transactional
    public ColumnLimit mapColumnLimitDtoToColumnLimit(ColumnLimitDto columnLimitDto) {
        Room room = roomRepository.findById(columnLimitDto.getRoomId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        String columns = columnNameConverter.columnNameArrayToString(columnLimitDto.getColumns());
        return new ColumnLimit(room, ColumnLimitType.MULTIPLE, columns, columnLimitDto.getLimitValue(), columnLimitDto.getIsActive());
    }
}
