CREATE TABLE daily_productivity (
daily_productivity_id uuid primary key,
productivity int,
day_id uuid references day,
room_member_id uuid references room_member
)
