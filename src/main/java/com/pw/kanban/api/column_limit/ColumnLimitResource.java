package com.pw.kanban.api.column_limit;

import com.pw.kanban.application.column_limit.CreateColumnLimitHandler;
import com.pw.kanban.application.column_limit.DeleteColumnLimitHandler;
import com.pw.kanban.application.column_limit.GetColumnLimitRepository;
import com.pw.kanban.application.column_limit.PatchColumnLimitHandler;
import com.pw.kanban.domain.column_limit.ColumnLimitDto;
import com.pw.kanban.domain.column_limit.ColumnLimitRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/columnLimit")
@RequiredArgsConstructor
public class ColumnLimitResource {
    private final GetColumnLimitRepository getColumnLimitRepository;
    private final CreateColumnLimitHandler createColumnLimitHandler;
    private final DeleteColumnLimitHandler deleteColumnLimitHandler;
    private final PatchColumnLimitHandler patchColumnLimitHandler;
    private final SimpMessagingTemplate msgTemplate;

    @GetMapping("/room/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    List<ColumnLimitRepresentation> getColumnLimits(@PathVariable UUID roomId) {
        return getColumnLimitRepository.getAllColumnLimitsByRoomId(roomId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ColumnLimitRepresentation createColumnLimit(@RequestBody ColumnLimitDto columnLimitDto) {
        ColumnLimitRepresentation columnLimitRepresentation = createColumnLimitHandler.handle(columnLimitDto);
        sendUpdatedColumnLimits(columnLimitDto.getRoomId());
        return columnLimitRepresentation;
    }

    @DeleteMapping("/{columnLimitId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteColumnLimit(@PathVariable UUID columnLimitId) {
        UUID roomId = getColumnLimitRepository.getColumnLimitRoomId(columnLimitId);
        deleteColumnLimitHandler.handle(columnLimitId);
        sendUpdatedColumnLimits(roomId);
    }

    @PatchMapping("/{columnLimitId}")
    @ResponseStatus(HttpStatus.OK)
    ColumnLimitRepresentation patchColumnLimit(@PathVariable UUID columnLimitId, @RequestBody ColumnLimitDto columnLimitDto) {
        UUID roomId = getColumnLimitRepository.getColumnLimitRoomId(columnLimitId);
        ColumnLimitRepresentation columnLimitRepresentation = patchColumnLimitHandler.handle(columnLimitDto, columnLimitId);
        sendUpdatedColumnLimits(roomId);
        return columnLimitRepresentation;
    }

    private void sendUpdatedColumnLimits(UUID roomId) {
        List<ColumnLimitRepresentation> columnLimitRepresentations = getColumnLimitRepository.getAllColumnLimitsByRoomId(roomId);
        msgTemplate.convertAndSend("/topic/room/" + roomId +"/columnLimits", columnLimitRepresentations);
    }


}
