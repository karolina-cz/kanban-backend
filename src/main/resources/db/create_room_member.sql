CREATE TABLE room_member(
room_member_id uuid primary key,
name text not null,
color text not null,
is_active boolean,
room_id uuid references room
)
