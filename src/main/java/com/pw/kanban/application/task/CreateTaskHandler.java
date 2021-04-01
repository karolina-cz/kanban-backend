package com.pw.kanban.application.task;

import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskDto;
import com.pw.kanban.domain.task.TaskRepository;
import com.pw.kanban.domain.task.TaskRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTaskHandler {
    private final TaskRepository taskRepository;
    private final TaskDtoMapper taskDtoMapper;
    private final TaskRepresentationMapper taskRepresentationMapper;

    @Transactional
    public TaskRepresentation handle(TaskDto taskDto) {
        //TODO generate task instead of passing arguments
        Task task = taskDtoMapper.mapTaskDtoToTask(taskDto);
        taskRepository.save(task);
        return taskRepresentationMapper.mapTaskToRepresentation(task);
    }
}
