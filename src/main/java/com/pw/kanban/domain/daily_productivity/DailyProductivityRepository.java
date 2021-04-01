package com.pw.kanban.domain.daily_productivity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DailyProductivityRepository extends JpaRepository<DailyProductivity, UUID> {
}
