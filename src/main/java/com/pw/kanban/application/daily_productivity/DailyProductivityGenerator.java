package com.pw.kanban.application.daily_productivity;

import com.pw.kanban.domain.daily_productivity.DailyProductivity;
import com.pw.kanban.domain.daily_productivity.DailyProductivityRepository;
import com.pw.kanban.domain.day.Day;
import com.pw.kanban.domain.room_member.RoomMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyProductivityGenerator {

    private final DailyProductivityRepository dailyProductivityRepository;

    @Transactional
    public void generateDailyProductivityForRoomMember(RoomMember roomMember) {
        List<Day> days = roomMember.getRoom().getDays();
        days.forEach(day -> {
            DailyProductivity dailyProductivity = new DailyProductivity(null, roomMember, day);
            dailyProductivityRepository.save(dailyProductivity);
        });
    }
}
