package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.assignee.AssigneeType;
import com.pw.kanban.domain.room_member.RoomMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RoomMemberProductivityComputer {

    public Double[] compute(RoomMember roomMember) {
        Double[] usedProductivity = new Double[RoomMemberDtoMapper.DAYS_COUNT];
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
        return usedProductivity;
    }
}
