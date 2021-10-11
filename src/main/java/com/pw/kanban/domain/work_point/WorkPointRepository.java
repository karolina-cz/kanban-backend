package com.pw.kanban.domain.work_point;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkPointRepository extends JpaRepository<WorkPoint, UUID> {
}
