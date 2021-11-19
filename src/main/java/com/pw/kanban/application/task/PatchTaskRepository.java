package com.pw.kanban.application.task;


import com.pw.kanban.application.assignee.PatchTaskAssigneeHandler;
import com.pw.kanban.application.room_member.MemberProductivityConverter;
import com.pw.kanban.domain.assignee.Assignee;
import com.pw.kanban.domain.assignee.AssigneeType;
import com.pw.kanban.domain.room.RoomType;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.room_member.RoomMemberRepository;
import com.pw.kanban.domain.room_member.RoomMemberType;
import com.pw.kanban.domain.task.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatchTaskRepository {

    private final TaskRepository taskRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final TaskRepresentationMapper taskRepresentationMapper;
    private final PatchTaskAssigneeHandler patchTaskAssigneeHandler;
    private final MemberProductivityConverter memberProductivityConverter;

    @Transactional
    public TaskRepresentation patchTask(TaskDto taskDto, UUID taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(taskDto.getKanbanColumn() != null) task.setKanbanColumn(taskDto.getKanbanColumn());
        if(taskDto.getStartDay() != null) task.setStartDay(taskDto.getStartDay());
        if(taskDto.getEndDay() != null) task.setEndDay(taskDto.getEndDay());
        if(taskDto.getEffort() != null) task.setEffort(taskDto.getEffort());
        if(taskDto.getRoomMembers() != null) {
            this.patchTaskAssigneeHandler.handle(taskId, taskDto.getRoomMembers().get(0));
        }
        if(canUnblockTask(task, taskDto)) {
            unblockTask(task, taskDto);
        }
        taskRepository.save(task);
        return taskRepresentationMapper.mapTaskToRepresentation(task);
    }

    @Transactional
    public List<TaskRepresentation> patchTasks(List<TaskDto> tasksDto) {
        // only case when multiple tasks are updated is when blockers are generated
        List<Task> tasks = new ArrayList<>();
        tasksDto.forEach(taskDto -> {
            Task task = taskRepository.findById(taskDto.getTaskId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND));
            if(taskDto.getIsBlocked() != null) {
                task.setBlocked(taskDto.getIsBlocked());
                tasks.add(task);
            }
        });
        taskRepository.saveAll(tasks);
        return taskRepresentationMapper.mapTasksToRepresentation(tasks);
    }

    private boolean canUnblockTask (Task task, TaskDto taskDto) {
        return  taskDto.getIsBlocked() != null && !taskDto.getIsBlocked() && task.isBlocked() && taskDto.getEditorId() != null &&
                taskDto.getDayModified() != null && taskDto.getDayModified() > 0 && taskDto.getDayModified() < 11;
    }

    private void unblockTask(Task task, TaskDto taskDto) {
        RoomMember roomMember = this.roomMemberRepository.findById(taskDto.getEditorId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (roomMember.getType() == RoomMemberType.PARTICIPANT) {
            Double[] unblockedTasks = memberProductivityConverter.stringToDoubleArray(roomMember.getUnblockedTasksProductivity());
            if (isMainAssignee(task, roomMember.getRoomMemberId())) {
                unblockedTasks[taskDto.getDayModified() - 1] = unblockedTasks[taskDto.getDayModified() - 1] == null ?
                        3.0 : unblockedTasks[taskDto.getDayModified() - 1] + 3.0;
                task.setBlocked(false);
            } else if (task.getRoom().getType() != RoomType.KANBAN_BOARD) {
                unblockedTasks[taskDto.getDayModified() - 1] = unblockedTasks[taskDto.getDayModified() - 1] == null ?
                        2.0 : unblockedTasks[taskDto.getDayModified() - 1] + 2.0;
                task.setBlocked(false);
            }
            roomMember.setUnblockedTasksProductivity(memberProductivityConverter.doubleArrayToString(unblockedTasks));
            roomMemberRepository.save(roomMember);
        }
    }

    private boolean isMainAssignee(Task task,UUID roomMemberId ) {

        List<Assignee> mainAssignee = task.getAssignees().stream()
                .filter(obj -> obj.getAssigneeType() == AssigneeType.MAIN)
                .collect(Collectors.toList());
        return mainAssignee.size() > 0 && mainAssignee.get(0).getRoomMember().getRoomMemberId() == roomMemberId;
    }
}
