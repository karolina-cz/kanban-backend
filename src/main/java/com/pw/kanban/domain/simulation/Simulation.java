package com.pw.kanban.domain.simulation;

import com.pw.kanban.domain.simulation_day.SimulationDay;
import com.pw.kanban.domain.task_template.TaskTemplate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="simulation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Simulation {

    @Id
    @GeneratedValue
    @Column(name = "simulation_id")
    private UUID simulationId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "simulation")
    @ToString.Exclude
    private List<SimulationDay> simulationDays;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "simulation")
    @ToString.Exclude
    private List<TaskTemplate> taskTemplates;

}

