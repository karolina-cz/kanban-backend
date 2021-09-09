package com.pw.kanban.domain.task;

import com.pw.kanban.domain.room_member.Color;
import lombok.Data;

@Data
public class WorkPointDto {
    int index;
    Color value;
}
