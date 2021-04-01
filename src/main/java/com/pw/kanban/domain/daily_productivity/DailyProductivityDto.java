package com.pw.kanban.domain.daily_productivity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class DailyProductivityDto {
    UUID dailyProductivityId;
    Integer productivity;
}
