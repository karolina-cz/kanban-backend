CREATE TABLE day (
    day_id uuid primary key,
    room_id uuid references room,
    day_number int not null,
    narrative text not null
)
