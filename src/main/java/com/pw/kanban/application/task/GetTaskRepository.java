package com.pw.kanban.application.task;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.task.TaskRepository;
import com.pw.kanban.domain.task.TaskRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTaskRepository {
    private final TaskRepository taskRepository;
    private final RoomRepository roomRepository;
    private final TaskRepresentationMapper taskRepresentationMapper;

    @Transactional
    public TaskRepresentation getTaskRepresentationById(UUID taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return taskRepresentationMapper.mapTaskToRepresentation(task);
    }

    @Transactional
    public Task getTaskById(UUID taskId) {
        return taskRepository.findById(taskId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public List<TaskRepresentation> getAllTasksByRoomId(UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Task> tasks = room.getTasks();
        return taskRepresentationMapper.mapTasksToRepresentation(tasks);
    }
}
