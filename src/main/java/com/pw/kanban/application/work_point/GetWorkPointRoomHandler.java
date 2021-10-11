package com.pw.kanban.application.work_point;

import com.pw.kanban.domain.work_point.WorkPoint;
import com.pw.kanban.domain.work_point.WorkPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetWorkPointRoomHandler {
    private final WorkPointRepository workPointRepository;

    @Transactional
    public UUID handle(UUID workPointId) {
        WorkPoint workPoint = workPointRepository.findById(workPointId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return workPoint.getTask().getRoom().getRoomId();
    }
}
