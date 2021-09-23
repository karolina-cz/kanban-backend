package com.pw.kanban.domain.room;

import com.pw.kanban.domain.column_limit.ColumnLimit;
import com.pw.kanban.domain.day.Day;
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
    @Column(name = "stage_one_limit")
    private int stageOneLimit;

    @NonNull
    @Column(name = "stage_one_in_progress_limit")
    private int stageOneInProgressLimit;

    @NonNull
    @Column(name = "stage_one_committed_limit")
    private int stageOneCommittedLimit;

    @NonNull
    @Column(name = "stage_one_done_limit")
    private int stageOneDoneLimit;

    @NonNull
    @Column(name = "stage_two_limit")
    private int stageTwoLimit;

    @Column(name = "done_limit")
    private int doneLimit;

    @Column(name = "blockers_probability")
    private Integer blockersProbability;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Task> tasks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    @ToString.Exclude
    private List<Day> days;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    @ToString.Exclude
    private List<RoomMember> roomMembers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    @ToString.Exclude
    private List<ColumnLimit> columnLimits;


    public Room(@NonNull RoomType type, @NonNull int stageOneLimit, @NonNull int stageOneInProgressLimit,
                @NonNull int stageOneCommittedLimit, @NonNull int stageOneDoneLimit,
                @NonNull int stageTwoLimit, int doneLimit, Integer blockersProbability) {
        this.type = type;
        this.stageOneLimit = stageOneLimit;
        this.stageOneInProgressLimit = stageOneInProgressLimit;
        this.stageOneCommittedLimit = stageOneCommittedLimit;
        this.stageOneDoneLimit = stageOneDoneLimit;
        this.stageTwoLimit = stageTwoLimit;
        this.doneLimit = doneLimit;
        this.blockersProbability = blockersProbability;
    }
}
