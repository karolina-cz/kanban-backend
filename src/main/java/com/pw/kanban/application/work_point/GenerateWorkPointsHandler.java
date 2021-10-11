package com.pw.kanban.application.work_point;

import com.pw.kanban.domain.task.Task;
import com.pw.kanban.domain.work_point.WorkPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenerateWorkPointsHandler {

    public List<WorkPoint> handle(Task task) {
        List<WorkPoint> workPoints = new ArrayList<>();
        for(int i = 0; i < 2; i++ ) {
            for(int j = 0; j < 5;j++) {
                workPoints.add(new WorkPoint(j, i, null, task, null));
            }
        }
        return workPoints;
    }
}
