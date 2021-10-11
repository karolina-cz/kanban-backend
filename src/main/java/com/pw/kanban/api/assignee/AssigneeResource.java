package com.pw.kanban.api.assignee;

import com.pw.kanban.application.assignee.CreateAssigneeHandler;
import com.pw.kanban.application.assignee.DeleteAssigneeHandler;
import com.pw.kanban.application.assignee.GetAssigneeRoomHandler;
import com.pw.kanban.application.room_member.SendRoomMembersHandler;
import com.pw.kanban.application.task.GetTaskRepository;
import com.pw.kanban.application.task.SendTasksHandler;
import com.pw.kanban.domain.assignee.AssigneeDto;
import com.pw.kanban.domain.assignee.AssigneeRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/assignee")
@RequiredArgsConstructor
public class AssigneeResource {
    private final GetTaskRepository getTaskRepository;
    private final SendTasksHandler sendTasksHandler;
    private final CreateAssigneeHandler createAssigneeHandler;
    private final DeleteAssigneeHandler deleteAssigneeHandler;
    private final GetAssigneeRoomHandler getAssigneeRoomHandler;
    private final SendRoomMembersHandler sendRoomMembersHandler;


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AssigneeRepresentation createAssignee( @RequestBody AssigneeDto assigneeDto) {
        AssigneeRepresentation assigneeRepresentation = createAssigneeHandler.handle(assigneeDto);
        UUID roomId = getTaskRepository.getTaskById(assigneeDto.getTaskId()).getRoom().getRoomId();
        this.sendTasksHandler.handle(roomId);
        this.sendRoomMembersHandler.handle(roomId);
        return assigneeRepresentation;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{assigneeId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteAssignee(@PathVariable UUID assigneeId) {
        UUID roomId = this.getAssigneeRoomHandler.handle(assigneeId);
        this.deleteAssigneeHandler.handle(assigneeId);
        this.sendTasksHandler.handle(roomId);
        this.sendRoomMembersHandler.handle(roomId);
    }

}
