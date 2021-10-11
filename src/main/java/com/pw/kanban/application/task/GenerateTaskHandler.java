package com.pw.kanban.application.task;
import com.pw.kanban.application.work_point.GenerateWorkPointsHandler;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import com.pw.kanban.domain.task.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenerateTaskHandler {
    private final TaskRepository taskRepository;
    private final RoomRepository roomRepository;
    private final TaskRepresentationMapper taskRepresentationMapper;
    private final WorkPointConverter workPointConverter;
    private final GenerateWorkPointsHandler generateWorkPointsHandler;
    public static final int POINTS_PER_STAGE = 5;

    @Transactional
    public TaskRepresentation handle(UUID roomId) {
        Room room = roomRepository.getOne(roomId);
        long standardTaskNumber = room.getTasks().stream()
                .filter(task -> task.getType() == TaskType.STANDARD)
                .count();
        String[] workPoints1 = new String[POINTS_PER_STAGE];
        String workPoints = workPointConverter.stringArrayToString(workPoints1);
        Task task = new Task(room, false, ColumnName.BACKLOG, null,
                null, null, null, TaskType.STANDARD,
                "S." + (standardTaskNumber + 1), workPoints, workPoints, null);
        taskRepository.save(task);
        task.setWorkPoints(generateWorkPointsHandler.handle(task));
        taskRepository.save(task);
        room.getTasks().add(task);
        roomRepository.save(room);
        return taskRepresentationMapper.mapTaskToRepresentation(task);
    }
}
