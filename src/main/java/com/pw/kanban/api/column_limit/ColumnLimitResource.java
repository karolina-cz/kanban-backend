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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/room/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    List<ColumnLimitRepresentation> getColumnLimits(@PathVariable UUID roomId) {
        return getColumnLimitRepository.getAllColumnLimitsByRoomId(roomId);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    List<ColumnLimitRepresentation> createColumnLimit(@RequestBody List<ColumnLimitDto> columnLimitDtos) {
        if (columnLimitDtos.size() > 0) {
            List<ColumnLimitRepresentation> columnLimitRepresentation = createColumnLimitHandler.handleMultiple(columnLimitDtos);
            sendUpdatedColumnLimits(columnLimitDtos.get(0).getRoomId());
            return columnLimitRepresentation;
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{columnLimitId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteColumnLimit(@PathVariable UUID columnLimitId) {
        UUID roomId = getColumnLimitRepository.getColumnLimitRoomId(columnLimitId);
        deleteColumnLimitHandler.handle(columnLimitId);
        sendUpdatedColumnLimits(roomId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    void deleteColumnLimits(@RequestBody List<ColumnLimitDto> columnLimitDtos) {
        if (columnLimitDtos.size() > 0) {
            UUID roomId = getColumnLimitRepository.getColumnLimitRoomId(columnLimitDtos.get(0).getColumnLimitId());
            deleteColumnLimitHandler.handleMultiple(columnLimitDtos);
            sendUpdatedColumnLimits(roomId);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/{columnLimitId}")
    @ResponseStatus(HttpStatus.OK)
    ColumnLimitRepresentation patchColumnLimit(@PathVariable UUID columnLimitId, @RequestBody ColumnLimitDto columnLimitDto) {
        UUID roomId = getColumnLimitRepository.getColumnLimitRoomId(columnLimitId);
        ColumnLimitRepresentation columnLimitRepresentation = patchColumnLimitHandler.handle(columnLimitDto, columnLimitId);
        sendUpdatedColumnLimits(roomId);
        return columnLimitRepresentation;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    List<ColumnLimitRepresentation> patchColumnsLimits(@RequestBody List<ColumnLimitDto> columnLimitDtos) {
        if (columnLimitDtos.size() > 0) {
            UUID roomId = getColumnLimitRepository.getColumnLimitRoomId(columnLimitDtos.get(0).getColumnLimitId());
            List<ColumnLimitRepresentation> columnLimitRepresentation = patchColumnLimitHandler.handleMultiple(columnLimitDtos);
            sendUpdatedColumnLimits(roomId);
            return columnLimitRepresentation;
        }
        return null;
    }

    private void sendUpdatedColumnLimits(UUID roomId) {
        List<ColumnLimitRepresentation> columnLimitRepresentations = getColumnLimitRepository.getAllColumnLimitsByRoomId(roomId);
        msgTemplate.convertAndSend("/topic/room/" + roomId +"/columnLimits", columnLimitRepresentations);
    }


}
