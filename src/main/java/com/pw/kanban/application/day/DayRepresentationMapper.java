package com.pw.kanban.application.day;

import com.pw.kanban.domain.day.Day;
import com.pw.kanban.domain.day.DayRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayRepresentationMapper {

    public DayRepresentation mapDayToRepresentation(Day day) {
        return new DayRepresentation(day.getDayId(), day.getDayNumber(), day.getNarrative());
    }

    public List<DayRepresentation> mapDaysToRepresentation(List<Day> days) {
        return days.stream().map(this::mapDayToRepresentation).collect(Collectors.toList());
    }
}
