package com.pw.kanban.domain.task_template;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskTemplateRepository extends JpaRepository<TaskTemplate, UUID> {
}
