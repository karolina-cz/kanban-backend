package com.pw.kanban.domain.day;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DayRepository extends JpaRepository<Day, UUID>{
}
