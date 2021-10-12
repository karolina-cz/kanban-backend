package com.pw.kanban.application.simulation_day;

import com.pw.kanban.domain.room.RoomType;
import com.pw.kanban.domain.simulation.SimulationRepository;
import com.pw.kanban.domain.simulation_day.SimulationDay;
import com.pw.kanban.domain.simulation_day.SimulationDayRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetSimulationDayHandler {
    private static final String KANBAN_SYSTEM_URL = "kanban-system";
    private static final String KANBAN_BOARD_URL = "kanban-board";
    private final SimulationRepository simulationRepository;
    private final SimulationDayRepresentationMapper simulationDayRepresentationMapper;

    @Transactional
    public List<SimulationDayRepresentation> handle(String simulationType) {
        RoomType simulationTypeEnum = simulationType.equals(KANBAN_SYSTEM_URL) ? RoomType.KANBAN_SYSTEM :
                simulationType.equals(KANBAN_BOARD_URL) ? RoomType.KANBAN_BOARD : null;
        if (simulationTypeEnum == null) return null;
        List<SimulationDay> sortedDays = simulationRepository
                .findBySimulationType(simulationTypeEnum)
                .get(0)
                .getSimulationDays()
                .stream()
                .sorted(Comparator.comparing(SimulationDay::getDayNumber))
                .collect(Collectors.toList());
        return simulationDayRepresentationMapper.mapSimulationDayToRepresentation(sortedDays);
    }
}
