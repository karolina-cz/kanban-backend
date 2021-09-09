package com.pw.kanban.domain.room_member;

import com.pw.kanban.domain.daily_productivity.DailyProductivity;
import com.pw.kanban.domain.room.Room;
import com.pw.kanban.domain.task.Task;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="room_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomMember {

    @Id
    @GeneratedValue
    @Column(name = "room_member_id")
    private UUID roomMemberId;

    @NonNull
    @Column(name="name")
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RoomMemberType type;

    @NonNull
    @Column(name="color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="room_id")
    private Room room;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomMember")
    @ToString.Exclude
    private List<DailyProductivity> dailyProductivityList;

    @ManyToMany(mappedBy = "assignees")
    private List<Task> tasks;

    @Column(name = "daily_productivity")
    private String dailyProductivity;

    public RoomMember(@NonNull String name, boolean isActive, @NonNull Color color, Room room, RoomMemberType type, String dailyProductivity) {
        this.name = name;
        this.isActive = isActive;
        this.color = color;
        this.room = room;
        this.type = type;
        this.dailyProductivity = dailyProductivity;
    }
}
