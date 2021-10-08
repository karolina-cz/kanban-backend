package com.pw.kanban.domain.assignee;

import lombok.Data;

import java.util.UUID;

@Data
public class AssigneeDto {
    UUID roomMemberId;
    UUID taskId;
    AssigneeType assigneeType;
}
