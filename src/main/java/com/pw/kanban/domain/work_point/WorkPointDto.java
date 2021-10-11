package com.pw.kanban.domain.work_point;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkPointDto {
    Integer dayModified;
    UUID assigneeId;
}
