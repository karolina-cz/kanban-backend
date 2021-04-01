package com.pw.kanban.api.task;

import com.pw.kanban.application.task.CreateTaskHandler;
import com.pw.kanban.application.task.PatchTaskAssigneeHandler;
import com.pw.kanban.domain.task.TaskDto;
import com.pw.kanban.domain.task.TaskRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task")
@RequiredArgsConstructor
public class TaskResource {
    private final CreateTaskHandler createTaskHandler;
    private final PatchTaskAssigneeHandler patchTaskAssigneeHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TaskRepresentation createTask(@RequestBody TaskDto taskDto){
        return createTaskHandler.handle(taskDto);
    }


    //TODO change void to sth else
    @PostMapping("/{taskId}/roomMember/{roomMemberId}")
    @ResponseStatus(HttpStatus.OK)
    void addAssigneeToTask(@PathVariable UUID taskId, @PathVariable UUID roomMemberId) {
        patchTaskAssigneeHandler.handle(taskId, roomMemberId);
    }

    //TODO get all tasks

    //TODO get task by id

    //TODO patch task

    //TODO work point patch, get all, get one by id

}
