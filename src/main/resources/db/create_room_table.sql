CREATE TABLE room (
    room_id uuid primary key,
    type text not null,
    stage_one_limit int not null,
    stage_one_committed_limit int not null,
    stage_one_in_progress_limit int not null,
    stage_one_done_limit int not null,
    stage_two_limit int not null,
    done_limit int not null
)
