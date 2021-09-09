package com.pw.kanban.application.task;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.task.ColumnName;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskDto;
import com.pw.kanban.domain.task.TaskType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskDtoMapper {
    private final RoomRepository roomRepository;
// TODO delte this class
//    @Transactional
//    public Task mapTaskDtoToTask(TaskDto taskDto) {
//        Room room = roomRepository.findById(taskDto.getRoomId()).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND));
//        int standardTaskNumber = (int) room.getTasks().stream()
//                .filter(task -> task.getType() == TaskType.STANDARD).count();
//        return new Task(room, false, ColumnName.BACKLOG, null,
//                null, null, null, TaskType.STANDARD, "S." + (standardTaskNumber + 1));
//    }
}
