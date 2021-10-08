package com.pw.kanban.application.task;


import com.pw.kanban.application.assignee.PatchTaskAssigneeHandler;
import com.pw.kanban.domain.task.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatchTaskRepository {

    private final TaskRepository taskRepository;
    private final TaskRepresentationMapper taskRepresentationMapper;
    private final WorkPointConverter workPointConverter;
    private final PatchTaskAssigneeHandler patchTaskAssigneeHandler;

    @Transactional
    public TaskRepresentation patchTask(TaskDto taskDto, UUID taskId) {
        // user should not be able to change task name, visibleFromDay, type or roomId
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(taskDto.getIsBlocked() != null) task.setBlocked(taskDto.getIsBlocked());
        if(taskDto.getKanbanColumn() != null) task.setKanbanColumn(taskDto.getKanbanColumn());
        if(taskDto.getStartDay() != null) task.setStartDay(taskDto.getStartDay());
        if(taskDto.getEndDay() != null) task.setEndDay(taskDto.getEndDay());
        if(taskDto.getEffort() != null) task.setEffort(taskDto.getEffort());
        if(taskDto.getWorkPoints1() != null) {
            task.setWorkPoints1(workPointConverter.colorArrayToString(taskDto.getWorkPoints1()));
        }
        if(taskDto.getWorkPoints2() != null) {
            task.setWorkPoints2(workPointConverter.colorArrayToString(taskDto.getWorkPoints2()));
        }
        if(taskDto.getRoomMembers() != null) {
            this.patchTaskAssigneeHandler.handle(taskId, taskDto.getRoomMembers().get(0));
        }
        taskRepository.save(task);
        return taskRepresentationMapper.mapTaskToRepresentation(task);
    }

    @Transactional
    public List<TaskRepresentation> patchTasks(List<TaskDto> tasksDto) {
        // only case when multiple tasks are updated is when blockers are generated
        List<Task> tasks = new ArrayList<>();
        tasksDto.forEach(taskDto -> {
            Task task = taskRepository.findById(taskDto.getTaskId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND));
            if(taskDto.getIsBlocked() != null) {
                task.setBlocked(taskDto.getIsBlocked());
                tasks.add(task);
            }
        });
        taskRepository.saveAll(tasks);
        return taskRepresentationMapper.mapTasksToRepresentation(tasks);
    }
}
