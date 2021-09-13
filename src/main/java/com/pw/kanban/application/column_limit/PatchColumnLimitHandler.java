package com.pw.kanban.application.column_limit;

import com.pw.kanban.domain.column_limit.ColumnLimit;
import com.pw.kanban.domain.column_limit.ColumnLimitDto;
import com.pw.kanban.domain.column_limit.ColumnLimitRepository;
import com.pw.kanban.domain.column_limit.ColumnLimitRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatchColumnLimitHandler {
    private final ColumnLimitRepository columnLimitRepository;
    private final ColumnNameConverter columnNameConverter;
    private final ColumnLimitRepresentationMapper columnLimitRepresentationMapper;

    @Transactional
    public ColumnLimitRepresentation handle(ColumnLimitDto columnLimitDto, UUID columnLimitId) {
        ColumnLimit columnLimit = columnLimitRepository.findById(columnLimitId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (columnLimitDto.getLimitValue() != null) columnLimit.setLimitValue(columnLimitDto.getLimitValue());
        if (columnLimitDto.getColumns() != null) {
            columnLimit.setColumns(columnNameConverter.columnNameArrayToString(columnLimitDto.getColumns()));
        }
        columnLimitRepository.save(columnLimit);
        return columnLimitRepresentationMapper.mapColumnLimitToRepresentation(columnLimit);
    }
}
