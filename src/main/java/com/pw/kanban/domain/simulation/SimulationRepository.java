package com.pw.kanban.domain.simulation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SimulationRepository extends JpaRepository<Simulation, UUID> {
}
