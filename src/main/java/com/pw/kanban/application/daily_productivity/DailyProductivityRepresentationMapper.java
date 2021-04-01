package com.pw.kanban.application.daily_productivity;

import com.pw.kanban.application.room_member.RoomMemberRepresentationMapper;
import com.pw.kanban.domain.daily_productivity.DailyProductivity;
import com.pw.kanban.domain.daily_productivity.DailyProductivityRepresentation;
import com.pw.kanban.domain.room_member.RoomMemberRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyProductivityRepresentationMapper {
    private final RoomMemberRepresentationMapper roomMemberRepresentationMapper;

    @Transactional
    public DailyProductivityRepresentation mapDailyProductivityToRepresentation(DailyProductivity dailyProductivity) {
        RoomMemberRepresentation roomMemberRepresentation = roomMemberRepresentationMapper
                .mapRoomMemberToRepresentation(dailyProductivity.getRoomMember());
        return new DailyProductivityRepresentation(dailyProductivity.getDailyProductivityId(), dailyProductivity.getProductivity(),
                dailyProductivity.getDay().getDayNumber(), roomMemberRepresentation);
    }

    @Transactional
    public List<DailyProductivityRepresentation> mapDailyProductivityToRepresentationList(
            List<DailyProductivity> dailyProductivity) {
        return dailyProductivity.stream()
                .map(this::mapDailyProductivityToRepresentation)
                .collect(Collectors.toList());
    }
}
