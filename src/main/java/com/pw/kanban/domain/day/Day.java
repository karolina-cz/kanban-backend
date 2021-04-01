package com.pw.kanban.domain.day;

import com.pw.kanban.domain.daily_productivity.DailyProductivity;
import com.pw.kanban.domain.room.Room;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="day")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Day {
    @Id
    @GeneratedValue
    @Column(name = "day_id")
    private UUID dayId;

    @NonNull
    @Column(name="day_number")
    private int dayNumber;

    @NonNull
    @Column(name="narrative")
    private String narrative;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="room_id")
    private Room room;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "day")
    @ToString.Exclude
    private List<DailyProductivity> dailyProductivityList;

    public Day(int dayNumber, String narrative, Room room) {
        this.dayNumber = dayNumber;
        this.narrative = narrative;
        this.room = room;
    }
}
