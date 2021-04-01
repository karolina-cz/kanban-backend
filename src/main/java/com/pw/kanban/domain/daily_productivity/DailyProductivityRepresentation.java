package com.pw.kanban.domain.daily_productivity;

import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DailyProductivityRepresentation {
    UUID dailyProductivityId;
    Integer productivity;
    Integer dayNumber;
    RoomMemberRepresentation roomMember;
}
