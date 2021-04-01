package com.pw.kanban.application.daily_productivity;

import com.pw.kanban.domain.daily_productivity.DailyProductivity;
import com.pw.kanban.domain.daily_productivity.DailyProductivityDto;
import com.pw.kanban.domain.daily_productivity.DailyProductivityRepository;
import com.pw.kanban.domain.daily_productivity.DailyProductivityRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatchDailyProductivityHandler {

    private final DailyProductivityRepository dailyProductivityRepository;
    private final DailyProductivityRepresentationMapper dailyProductivityRepresentationMapper;

    @Transactional
    public List<DailyProductivityRepresentation> handle(List<DailyProductivityDto> dailyProductivityDtos) {
        List<DailyProductivity> dailyProductivityList = new ArrayList<>();
        dailyProductivityDtos.forEach(dailyProductivityDto -> {
            DailyProductivity dailyProductivity = dailyProductivityRepository
                .findById(dailyProductivityDto.getDailyProductivityId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            if(dailyProductivityDto.getProductivity() != null) dailyProductivity
                    .setProductivity(dailyProductivityDto.getProductivity());
            dailyProductivityList.add(dailyProductivity);
            dailyProductivityRepository.save(dailyProductivity);
        });
        return dailyProductivityRepresentationMapper.mapDailyProductivityToRepresentationList(dailyProductivityList);

    }
}
