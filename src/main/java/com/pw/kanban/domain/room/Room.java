package com.pw.kanban.domain.room;

import com.pw.kanban.domain.column_limit.ColumnLimit;
import com.pw.kanban.domain.room_member.RoomMember;
import com.pw.kanban.domain.task.Task;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private UUID roomId;

    @NonNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @NonNull
    @Column(name = "current_day")
    private Integer currentDay;

    @Column(name = "blockers_probability")
    private Integer blockersProbability;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Task> tasks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<RoomMember> roomMembers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    @ToString.Exclude
    private List<ColumnLimit> columnLimits;


    public Room(@NonNull RoomType type, Integer blockersProbability, @NonNull Integer currentDay) {
        this.type = type;
        this.blockersProbability = blockersProbability;
        this.currentDay = currentDay;
    }
}
