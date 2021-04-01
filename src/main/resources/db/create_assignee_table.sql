create table assignee (
    task_id uuid not null references task,
    room_member_id not null references room_member
)
