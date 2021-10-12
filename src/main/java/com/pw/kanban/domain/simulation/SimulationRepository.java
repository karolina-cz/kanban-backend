package com.pw.kanban.domain.simulation;

import com.pw.kanban.domain.room.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SimulationRepository extends JpaRepository<Simulation, UUID> {
    List<Simulation> findBySimulationType(RoomType simulationType);
}
