CREATE TABLE room
(
    room_id              uuid primary key,
    type                 text not null,
    blockers_probability int
);

CREATE TABLE room_member
(
    room_member_id     uuid primary key,
    name               text not null,
    color              text not null,
    type               text,
    room_id            uuid references room,
    daily_productivity text not null
);

CREATE TABLE task
(
    task_id          uuid primary key,
    room_id          uuid references room,
    is_blocked       boolean not null,
    kanban_column    text    not null,
    start_day        int,
    end_day          int,
    visible_from_day int,
    effort           float8,
    name             text,
    type             text    not null,
    work_points1     text    not null,
    work_points2     text    not null,
    due_day          int
);

CREATE TABLE simulation
(
    simulation_id   uuid primary key,
    simulation_type text not null
);

CREATE TABLE simulation_day
(
    simulation_day_id uuid primary key,
    simulation_id     uuid references simulation,
    day_number        int  not null,
    narrative         text not null
);

CREATE TABLE task_template
(
    task_template_id uuid primary key,
    simulation_id    uuid references simulation,
    tasks_number     int  not null,
    tasks_type       text not null,
    visible_from_day int,
    due_day          int
);

CREATE TABLE column_limit
(
    column_limit_id uuid primary key,
    room_id         uuid references room,
    limit_type      text not null,
    columns         text not null,
    is_active       boolean,
    limit_value     int
);

create table assignee
(
    assignee_id    uuid primary key,
    task_id        uuid not null references task,
    room_member_id uuid not null references room_member,
    assignee_type  text
);

CREATE TABLE work_point
(
    work_point_id uuid primary key,
    point_index   int not null,
    stage         int not null,
    day_modified  int,
    assignee_id   uuid references assignee,
    task_id       uuid references task
);
