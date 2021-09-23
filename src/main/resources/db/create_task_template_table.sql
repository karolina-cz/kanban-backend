CREATE TABLE task_template (
    task_template_id uuid primary key,
    simulation_id uuid references simulation,
    tasks_number int not null,
    tasks_type text not null,
    visible_from_day int,
    due_day int
)
