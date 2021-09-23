package com.pw.kanban.application.column_limit;

import com.pw.kanban.domain.column_limit.ColumnLimit;
import com.pw.kanban.domain.column_limit.ColumnLimitDto;
import com.pw.kanban.domain.column_limit.ColumnLimitRepository;
import com.pw.kanban.domain.column_limit.ColumnLimitRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<ColumnLimitRepresentation> handleMultiple(List<ColumnLimitDto> columnLimitDtos) {
        if(columnLimitDtos == null) return Collections.emptyList();
        return columnLimitDtos.stream()
                .map(this::handle)
                .collect(Collectors.toList());
    }
}
