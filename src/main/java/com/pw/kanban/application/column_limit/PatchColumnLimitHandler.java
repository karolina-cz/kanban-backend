package com.pw.kanban.application.column_limit;

import com.pw.kanban.domain.column_limit.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        if (columnLimitDto.getColumns() != null && columnLimit.getLimitType() == ColumnLimitType.MULTIPLE) {
            columnLimit.setColumns(columnNameConverter.columnNameArrayToString(columnLimitDto.getColumns()));
        }
        if (columnLimitDto.getIsActive() != null) columnLimit.setIsActive(columnLimitDto.getIsActive());
        columnLimitRepository.save(columnLimit);
        return columnLimitRepresentationMapper.mapColumnLimitToRepresentation(columnLimit);
    }

    @Transactional
    public List<ColumnLimitRepresentation> handleMultiple(List<ColumnLimitDto> columnLimitDtos) {
        if(columnLimitDtos == null) return Collections.emptyList();
        return columnLimitDtos.stream()
                .map(element -> this.handle(element, element.getColumnLimitId()))
                .collect(Collectors.toList());
    }
}
