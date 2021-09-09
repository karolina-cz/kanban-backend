package com.pw.kanban.api.task;

import com.pw.kanban.application.task.*;
import com.pw.kanban.domain.task.TaskDto;
import com.pw.kanban.domain.task.TaskRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task")
@RequiredArgsConstructor
public class TaskResource {
    private final GenerateTaskHandler generateTaskHandler;
    private final PatchTaskAssigneeHandler patchTaskAssigneeHandler;
    private final GetTaskRepository getTaskRepository;
    private final PatchTaskRepository patchTaskRepository;
    private final DeleteTaskAssigneeHandler deleteTaskAssigneeHandler;
    private final SimpMessagingTemplate msgTemplate;
    private final CreateTaskAssigneeHandler createTaskAssigneeHandler;

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


    //TODO OPTIONAL change void to sth else
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/{taskId}/roomMember/{roomMemberId}")
    @ResponseStatus(HttpStatus.OK)
    void addAssigneeToTask(@PathVariable UUID taskId, @PathVariable UUID roomMemberId) {
        createTaskAssigneeHandler.handle(taskId, roomMemberId);
        UUID roomId = getTaskRepository.getTaskById(taskId).getRoom().getRoomId();
        this.sendUpdatedTasks(roomId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{taskId}/roomMember/{roomMemberId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteAssigneeFromTask(@PathVariable UUID taskId, @PathVariable UUID roomMemberId){
        deleteTaskAssigneeHandler.handle(taskId, roomMemberId);
        UUID roomId = getTaskRepository.getTaskById(taskId).getRoom().getRoomId();
        this.sendUpdatedTasks(roomId);
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @PatchMapping("/{taskId}/roomMember/{roomMemberId}")
//    @ResponseStatus(HttpStatus.OK)
//    void patchTaskAssignee(@PathVariable UUID taskId, @PathVariable UUID roomMemberId){
//        patchTaskAssigneeHandler.handle(taskId, roomMemberId);
//        UUID roomId = getTaskRepository.getTaskById(taskId).getRoom().getRoomId();
//        this.sendUpdatedTasks(roomId);
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    TaskRepresentation patchTask(@PathVariable UUID taskId, @RequestBody TaskDto taskDto) {
        TaskRepresentation taskRepresentation = patchTaskRepository.patchTask(taskDto, taskId);
        UUID roomId = getTaskRepository.getTaskById(taskId).getRoom().getRoomId();
        this.sendUpdatedTasks(roomId);
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
