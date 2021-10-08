package com.pw.kanban.api.simulation_day;

import com.pw.kanban.application.simulation_day.GetSimulationDayHandler;
import com.pw.kanban.domain.simulation_day.SimulationDayRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulationDay")
@RequiredArgsConstructor
public class SimulationDayResource {
    private final GetSimulationDayHandler simulationDayHandler;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<SimulationDayRepresentation> getAllSimulationDays() {
        return simulationDayHandler.handle();
    }
}
