package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.room_member.Color;
import com.pw.kanban.domain.room_member.RoomMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomMemberColorGenerator {

    public Color generate(List<RoomMember> roomMembers) {
        List<Color> colors = roomMembers.stream().map(RoomMember::getColor).collect(Collectors.toList());
        Color result = Color.BLACK;
        for(Color color: Color.values()){
            if(!(color == Color.BLACK) && !(colors.contains(color))) {
                result = color;
                break;
            }
        }
        return result;
    }
}
