package com.pw.kanban.application.day;

import com.pw.kanban.domain.day.Day;
import com.pw.kanban.domain.day.DayRepresentation;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetDaysHandler {
    private final RoomRepository roomRepository;
    private final DayRepresentationMapper dayRepresentationMapper;

    @Transactional
    public List<DayRepresentation> handle(UUID roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.OK));
        return dayRepresentationMapper.mapDaysToRepresentation(room.getDays());
    }
}
