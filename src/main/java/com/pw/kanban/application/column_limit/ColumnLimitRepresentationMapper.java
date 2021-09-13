package com.pw.kanban.application.column_limit;

import com.pw.kanban.domain.column_limit.ColumnLimit;
import com.pw.kanban.domain.column_limit.ColumnLimitRepresentation;
import com.pw.kanban.domain.task.ColumnName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColumnLimitRepresentationMapper {
    private final ColumnNameConverter columnNameConverter;

    public ColumnLimitRepresentation mapColumnLimitToRepresentation(ColumnLimit columnLimit) {
        if(columnLimit == null) return null;
        List<ColumnName> columns = columnNameConverter.stringToColumnNameArray(columnLimit.getColumns());
        return new ColumnLimitRepresentation(columnLimit.getColumnLimitId(), columnLimit.getLimitType(), columns,
                columnLimit.getLimitValue());
    }

    public List<ColumnLimitRepresentation> mapColumnLimitsToRepresentation(List<ColumnLimit> columnLimits) {
        return columnLimits.stream().map(this::mapColumnLimitToRepresentation).collect(Collectors.toList());
    }
}
