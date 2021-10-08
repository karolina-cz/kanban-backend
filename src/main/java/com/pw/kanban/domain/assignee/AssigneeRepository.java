package com.pw.kanban.domain.assignee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssigneeRepository extends JpaRepository<Assignee, UUID> {
}
