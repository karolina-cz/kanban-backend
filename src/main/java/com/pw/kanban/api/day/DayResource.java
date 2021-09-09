package com.pw.kanban.api.day;

import com.pw.kanban.application.day.GetDaysHandler;
import com.pw.kanban.domain.day.DayRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/day")
@RequiredArgsConstructor
public class DayResource {
    private final GetDaysHandler getDaysHandler;

    @GetMapping("/room/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    List<DayRepresentation> getDays(@PathVariable UUID roomId) {
        return getDaysHandler.handle(roomId);
    }
}
