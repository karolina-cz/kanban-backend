package com.pw.kanban.api.daily_productivity;

import com.pw.kanban.application.daily_productivity.GetDailyProductivityHandler;
import com.pw.kanban.application.daily_productivity.PatchDailyProductivityHandler;
import com.pw.kanban.domain.daily_productivity.DailyProductivityDto;
import com.pw.kanban.domain.daily_productivity.DailyProductivityRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dailyProductivity")
@RequiredArgsConstructor
public class DailyProductivityResource {

    private final PatchDailyProductivityHandler patchDailyProductivityHandler;
    private final GetDailyProductivityHandler getDailyProductivityHandler;

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    List<DailyProductivityRepresentation> patchDailyProductivity(@RequestBody List<DailyProductivityDto> dailyProductivityDto) {
        return patchDailyProductivityHandler.handle(dailyProductivityDto);
    }

    @GetMapping("/day/{dayId}")
    @ResponseStatus(HttpStatus.OK)
    List<DailyProductivityRepresentation> getAllDailyProductivityForRoom(@PathVariable UUID dayId) {
        return getDailyProductivityHandler.handle(dayId);
    }


}
