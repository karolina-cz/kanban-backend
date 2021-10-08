package com.pw.kanban.application.task;

import com.pw.kanban.domain.task.TaskRepresentation;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SendTasksHandler {
    private final GetTaskRepository getTaskRepository;
    private final SimpMessagingTemplate msgTemplate;

    public void handle(UUID roomId) {
        List<TaskRepresentation> taskRepresentationList = getTaskRepository.getAllTasksByRoomId(roomId);
        msgTemplate.convertAndSend("/topic/room/" + roomId +"/tasks", taskRepresentationList);
    }
}
