package com.pw.kanban.application.assignee;

import com.pw.kanban.application.room_member.MemberProductivityConverter;
import com.pw.kanban.domain.assignee.Assignee;
import com.pw.kanban.domain.assignee.AssigneeRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssigneeRepresentationMapper {
    private final MemberProductivityConverter memberProductivityConverter;

    public AssigneeRepresentation mapAssigneeToRepresentation(Assignee assignee) {
        Double[] dailyProductivity = memberProductivityConverter.stringToDoubleArray(assignee.getRoomMember().getDailyProductivity());
        return new AssigneeRepresentation(assignee.getAssigneeId(), assignee.getRoomMember().getRoomMemberId(), assignee.getRoomMember().getName(),
                assignee.getRoomMember().getColor(), assignee.getRoomMember().isActive(), assignee.getRoomMember().getType(), dailyProductivity,
                assignee.getAssigneeType());
    }

    public List<AssigneeRepresentation> mapAssigneesToRepresentation(List<Assignee> assignees) {
        if(assignees == null) return Collections.emptyList();
        return assignees.stream()
                .map(this::mapAssigneeToRepresentation)
                .collect(Collectors.toList());
    }
}
