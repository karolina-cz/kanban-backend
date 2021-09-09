CREATE TABLE task(
    task_id uuid primary key,
    room_id uuid references room,
    is_blocked boolean not null,
    kanban_column text not null,
    start_day int,
    end_day int,
    visible_from_day int,
    effort numeric,
    name text,
    type text not null,
    work_points1 text not null,
    work_points2 text not null,
    due_day int
)
