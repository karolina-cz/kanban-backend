package com.pw.kanban.application.simulation_day;

import com.pw.kanban.domain.simulation_day.SimulationDay;
import com.pw.kanban.domain.simulation_day.SimulationDayRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimulationDayRepresentationMapper {
    public List<SimulationDayRepresentation> mapSimulationDayToRepresentation(List<SimulationDay> simulationDayList) {
        if(simulationDayList == null) return Collections.emptyList();
        return simulationDayList
                .stream()
                .map(simulationDay -> new SimulationDayRepresentation(simulationDay.getSimulationDayId(),
                        simulationDay.getDayNumber(),
                        simulationDay.getNarrative()))
                .collect(Collectors.toList());
    }
}
