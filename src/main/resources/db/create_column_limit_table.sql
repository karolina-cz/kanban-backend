CREATE TABLE column_limit(
    column_limit_id uuid primary key,
    room_id uuid references room,
    limit_type text not null,
    columns text not null,
    is_active boolean,
    limit_value int
)
