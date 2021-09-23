package com.pw.kanban.domain.simulation_day;

import com.pw.kanban.domain.simulation.Simulation;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="simulation_day")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SimulationDay {

    @Id
    @GeneratedValue
    @Column(name = "simulation_day_id")
    private UUID simulationDayId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="simulation_id")
    private Simulation simulation;

    @NonNull
    @Column(name = "day_number")
    private int dayNumber;

    @NonNull
    @Column(name = "narrative")
    private String narrative;

    public SimulationDay(Simulation simulation, int dayNumber, String narrative) {
        this.simulation = simulation;
        this.dayNumber = dayNumber;
        this.narrative = narrative;
    }
}
