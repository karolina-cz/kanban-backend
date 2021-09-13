package com.pw.kanban.domain.column_limit;

import com.pw.kanban.domain.task.ColumnName;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ColumnLimitDto {
    UUID columnLimitId;
    UUID roomId;
    ColumnLimitType limitType;
    List<ColumnName> columns;
    Integer limitValue;
}
