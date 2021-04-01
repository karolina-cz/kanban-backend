package com.pw.kanban.domain.daily_productivity;


import com.pw.kanban.domain.day.Day;
import com.pw.kanban.domain.room_member.RoomMember;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="daily_productivity")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyProductivity {

    @Id
    @GeneratedValue
    @Column(name = "daily_productivity_id")
    private UUID dailyProductivityId;

    @Column(name = "productivity")
    private Integer productivity;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="room_member_id")
    private RoomMember roomMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="day_id")
    private Day day;

    public DailyProductivity(Integer productivity, RoomMember roomMember, Day day) {
        this.productivity = productivity;
        this.roomMember = roomMember;
        this.day = day;
    }
}
