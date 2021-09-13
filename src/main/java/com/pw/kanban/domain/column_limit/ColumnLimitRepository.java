package com.pw.kanban.domain.column_limit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColumnLimitRepository extends JpaRepository<ColumnLimit, UUID> {
}
