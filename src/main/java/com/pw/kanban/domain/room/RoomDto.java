package com.pw.kanban.domain.room;

import lombok.Data;

@Data
public class RoomDto {
    RoomType roomType;
    Integer stageOneLimit;
    Integer stageOneInProgressLimit;
    Integer stageOneCommittedLimit;
    Integer stageOneDoneLimit;
    Integer stageTwoLimit;
    Integer doneLimit;
}
