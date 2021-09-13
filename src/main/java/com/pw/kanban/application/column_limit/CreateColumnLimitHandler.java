package com.pw.kanban.application.column_limit;

import com.pw.kanban.domain.column_limit.ColumnLimit;
import com.pw.kanban.domain.column_limit.ColumnLimitDto;
import com.pw.kanban.domain.column_limit.ColumnLimitRepository;
import com.pw.kanban.domain.column_limit.ColumnLimitRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateColumnLimitHandler {
    private final ColumnLimitDtoMapper columnLimitDtoMapper;
    private final ColumnLimitRepository columnLimitRepository;
    private final ColumnLimitRepresentationMapper columnLimitRepresentationMapper;

    @Transactional
    public ColumnLimitRepresentation handle(ColumnLimitDto columnLimitDto) {
        ColumnLimit columnLimit = columnLimitDtoMapper.mapColumnLimitDtoToColumnLimit(columnLimitDto);
        columnLimit = columnLimitRepository.save(columnLimit);
        return columnLimitRepresentationMapper.mapColumnLimitToRepresentation(columnLimit);
    }
}
