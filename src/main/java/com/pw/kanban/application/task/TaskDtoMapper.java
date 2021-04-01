package com.pw.kanban.application.task;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TaskDtoMapper {
    private final RoomRepository roomRepository;

    public Task mapTaskDtoToTask(TaskDto taskDto) {
        Room room = roomRepository.findById(taskDto.getRoomId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new Task(room, taskDto.isBlocked(), taskDto.getKanbanColumn(), taskDto.getStartDay(),
                taskDto.getEndDay(), taskDto.getEffort(), task)
    }
}
