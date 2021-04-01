CREATE TABLE work_point (
work_point_id uuid primary key,
point_index int not null,
stage int not null,
room_member_id uuid references room_member,
task_id uuid references task
)

