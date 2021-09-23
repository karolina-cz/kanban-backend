CREATE TABLE simulation_day (
    simulation_day_id uuid primary key,
    simulation_id uuid references simulation,
    day_number int not null,
    narrative text not null
)
