package com.pw.kanban.domain.column_limit;

import com.pw.kanban.domain.task.ColumnName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ColumnLimitRepresentation {
    UUID columnLimitId;
    ColumnLimitType limitType;
    List<ColumnName> columns;
    Integer limitValue;
    Boolean isActive;
}
