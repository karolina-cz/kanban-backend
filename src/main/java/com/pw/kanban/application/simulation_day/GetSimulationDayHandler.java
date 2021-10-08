package com.pw.kanban.application.simulation_day;

import com.pw.kanban.domain.simulation_day.SimulationDayRepository;
import com.pw.kanban.domain.simulation_day.SimulationDayRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSimulationDayHandler {
    private final SimulationDayRepository simulationDayRepository;
    private final SimulationDayRepresentationMapper simulationDayRepresentationMapper;

    @Transactional
    public List<SimulationDayRepresentation> handle() {
        return simulationDayRepresentationMapper.mapSimulationDayToRepresentation(simulationDayRepository.findAll());
    }
}
