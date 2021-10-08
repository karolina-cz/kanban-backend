package com.pw.kanban.domain.simulation_day;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class SimulationDayRepresentation {
    UUID simulationDayId;
    int dayNumber;
    String narrative;
}
