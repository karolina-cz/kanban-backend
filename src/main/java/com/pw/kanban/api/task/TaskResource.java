package com.pw.kanban.api.task;

import com.pw.kanban.application.assignee.CreateAssigneeHandler;
import com.pw.kanban.application.assignee.DeleteAssigneeHandler;
import com.pw.kanban.application.room_member.SendRoomMembersHandler;
import com.pw.kanban.application.task.*;
import com.pw.kanban.domain.task.TaskDto;
import com.pw.kanban.domain.task.TaskRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task")
@RequiredArgsConstructor
public class TaskResource {
    private final GenerateTaskHandler generateTaskHandler;
    private final GetTaskRepository getTaskRepository;
    private final PatchTaskRepository patchTaskRepository;
    private final DeleteAssigneeHandler deleteAssigneeHandler;
    private final SimpMessagingTemplate msgTemplate;
    private final CreateAssigneeHandler createAssigneeHandler;
    private final SendRoomMembersHandler sendRoomMembersHandler;

    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    TaskRepresentation getTask(@PathVariable UUID taskId) {

        return getTaskRepository.getTaskRepresentationById(taskId);
    }

    @GetMapping("/room/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    List<TaskRepresentation> getTasks(@PathVariable UUID roomId) {
        return getTaskRepository.getAllTasksByRoomId(roomId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/room/{roomId}")
    @ResponseStatus(HttpStatus.CREATED)
    TaskRepresentation generateTask(@PathVariable UUID roomId) {
        TaskRepresentation taskRepresentation = generateTaskHandler.handle(roomId);
//        this.sendUpdatedTasks(roomId);
        List<TaskRepresentation> taskRepresentationList = getTaskRepository.getAllTasksByRoomId(roomId);
        msgTemplate.convertAndSend("/topic/room/" + roomId +"/tasks", taskRepresentationList);
        return taskRepresentation;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    TaskRepresentation patchTask(@PathVariable UUID taskId, @RequestBody TaskDto taskDto) {
        TaskRepresentation taskRepresentation = patchTaskRepository.patchTask(taskDto, taskId);
        UUID roomId = getTaskRepository.getTaskById(taskId).getRoom().getRoomId();
        this.sendUpdatedTasks(roomId);
        this.sendRoomMembersHandler.handle(roomId);
        return taskRepresentation;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    List<TaskRepresentation> patchTasks(@RequestBody List<TaskDto> tasksDto) {
        if(tasksDto.size() == 0) return Collections.emptyList();
        List<TaskRepresentation> taskRepresentations = patchTaskRepository.patchTasks(tasksDto);
        UUID roomId = getTaskRepository.getTaskById(taskRepresentations.get(0).getTaskId()).getRoom().getRoomId();
        this.sendUpdatedTasks(roomId);
        return taskRepresentations;
    }

    public void sendUpdatedTasks(UUID roomId) {
        List<TaskRepresentation> taskRepresentationList = getTaskRepository.getAllTasksByRoomId(roomId);
        msgTemplate.convertAndSend("/topic/room/" + roomId +"/tasks", taskRepresentationList);
    }
}
