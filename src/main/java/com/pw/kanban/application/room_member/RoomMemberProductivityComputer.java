package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.assignee.AssigneeType;
import com.pw.kanban.domain.room_member.RoomMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RoomMemberProductivityComputer {
    private final MemberProductivityConverter memberProductivityConverter;

    public Double[] compute(RoomMember roomMember) {
        Double[] usedProductivity = new Double[RoomMemberDtoMapper.DAYS_COUNT];
        Double[] unblockedTasks = memberProductivityConverter.stringToDoubleArray(roomMember.getUnblockedTasksProductivity());
        Arrays.fill(usedProductivity, 0.0);
        if (roomMember.getAssignees() != null) {
            roomMember.getAssignees().forEach(assignee -> {
                if (assignee.getWorkPoints() != null) {
                    assignee.getWorkPoints().forEach(workPoint -> {
                        Double workLoad = assignee.getAssigneeType() == AssigneeType.MAIN ? 1.0 : 2.0;
                        usedProductivity[workPoint.getDayModified()] = usedProductivity[workPoint.getDayModified()] + workLoad;
                    });
                }
            });
        }
        for (int i = 0; i < RoomMemberDtoMapper.DAYS_COUNT; i++) {
            if (usedProductivity[i] == null) {
                usedProductivity[i] = unblockedTasks[i];
            } else if (unblockedTasks[i] != null) {
                usedProductivity[i] += unblockedTasks[i];
            } // todo przetestowac
        }
        return usedProductivity;
    }
}
