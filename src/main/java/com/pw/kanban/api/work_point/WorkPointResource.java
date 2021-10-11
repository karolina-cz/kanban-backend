package com.pw.kanban.api.work_point;

import com.pw.kanban.application.room_member.SendRoomMembersHandler;
import com.pw.kanban.application.task.SendTasksHandler;
import com.pw.kanban.application.work_point.GetWorkPointRoomHandler;
import com.pw.kanban.application.work_point.PatchWorkPointHandler;
import com.pw.kanban.domain.work_point.WorkPointDto;
import com.pw.kanban.domain.work_point.WorkPointRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/workPoint")
@RequiredArgsConstructor
public class WorkPointResource {
    private final PatchWorkPointHandler patchWorkPointHandler;
    private final GetWorkPointRoomHandler getWorkPointRoomHandler;
    private final SendTasksHandler sendTasksHandler;
    private final SendRoomMembersHandler sendRoomMembersHandler;

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/{workPointId}")
    @ResponseStatus(HttpStatus.OK)
    WorkPointRepresentation patchWorkPoint(@PathVariable UUID workPointId, @RequestBody WorkPointDto workPointDto) {
        UUID roomId = getWorkPointRoomHandler.handle(workPointId);
        WorkPointRepresentation workPointRepresentation = patchWorkPointHandler.handle(workPointDto, workPointId);
        sendTasksHandler.handle(roomId);
        sendRoomMembersHandler.handle(roomId);
        return workPointRepresentation;
    }

}
