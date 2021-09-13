package com.pw.kanban.domain.column_limit;

import com.pw.kanban.domain.room.Room;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="column_limit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ColumnLimit {
    @Id
    @GeneratedValue
    @Column(name = "column_limit_id")
    private UUID columnLimitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="room_id")
    private Room room;

    @NonNull
    @Column(name="limit_type")
    @Enumerated(EnumType.STRING)
    private ColumnLimitType limitType;

    @NonNull
    @Column(name="columns")
    private String columns;

    @Column(name="limit_value")
    private Integer limitValue;

    public ColumnLimit(Room room, ColumnLimitType limitType, String columns, Integer limitValue) {
        this.room = room;
        this.limitType = limitType;
        this.columns = columns;
        this.limitValue = limitValue;
    }

}
