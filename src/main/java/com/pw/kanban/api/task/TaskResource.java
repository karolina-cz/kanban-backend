package com.pw.kanban.api.task;

import com.pw.kanban.domain.task.TaskRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@RequestMapping(path = "/api/task")
@RequiredArgsConstructor
public class TaskResource {
    private

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TaskRepresentation createTask(@PathVariable String roomId){
        return null;
    }

}
