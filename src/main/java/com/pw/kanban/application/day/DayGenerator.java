package com.pw.kanban.application.day;

import com.pw.kanban.domain.day.Day;
import com.pw.kanban.domain.day.DayRepository;
import com.pw.kanban.domain.room.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DayGenerator {
    private final static Integer daysNumber = 2;
    private final DayRepository dayRepository;

    @Transactional
    public void generateDaysForRoom(Room room) {
        for(int i = 0; i < daysNumber; i++) {
            Day day = new Day(i+1, "", room);
            dayRepository.save(day);
        }
    }
}
