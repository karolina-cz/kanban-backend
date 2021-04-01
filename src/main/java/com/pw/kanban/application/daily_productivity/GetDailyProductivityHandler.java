package com.pw.kanban.application.daily_productivity;

import com.pw.kanban.domain.daily_productivity.DailyProductivityRepresentation;
import com.pw.kanban.domain.day.Day;
import com.pw.kanban.domain.day.DayRepository;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetDailyProductivityHandler {
    private final DayRepository dayRepository;
    private final DailyProductivityRepresentationMapper dailyProductivityRepresentationMapper;

    @Transactional
    public List<DailyProductivityRepresentation> handle(UUID dayId) {
        Day day = dayRepository.findById(dayId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return dailyProductivityRepresentationMapper
                .mapDailyProductivityToRepresentationList(day.getDailyProductivityList());
    }
}
