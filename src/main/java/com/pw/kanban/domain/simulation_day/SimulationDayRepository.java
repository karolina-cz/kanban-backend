package com.pw.kanban.domain.simulation_day;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SimulationDayRepository extends JpaRepository<SimulationDay, UUID> {
}
