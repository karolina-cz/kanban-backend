package com.pw.kanban.application.column_limit;

import com.pw.kanban.domain.column_limit.ColumnLimit;
import com.pw.kanban.domain.column_limit.ColumnLimitRepository;
import com.pw.kanban.domain.column_limit.ColumnLimitType;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.task.ColumnName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColumnLimitGenerator {
    private final ColumnLimitRepository columnLimitRepository;

    @Transactional
    public void generateColumnLimits(Room room) {
        for (ColumnName columnName : ColumnName.values()) {
            ColumnLimit columnLimit = new ColumnLimit(room, ColumnLimitType.SINGLE, columnName.toString(), null, false);
            columnLimitRepository.save(columnLimit);
        }
    }
}
