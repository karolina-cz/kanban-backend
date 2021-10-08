CREATE TABLE work_point (
work_point_id uuid primary key,
point_index int not null,
stage int not null,
day_modified int,
assignee_id uuid references assignee,
task_id uuid references task
)

