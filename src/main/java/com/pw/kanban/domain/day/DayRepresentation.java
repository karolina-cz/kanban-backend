package com.pw.kanban.domain.day;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DayRepresentation {
    UUID dayId;
    Integer day;
    String narrative;
}
