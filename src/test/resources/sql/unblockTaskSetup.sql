INSERT INTO room (room_id, type, current_day, blockers_probability)
VALUES ('0910ab8a-27d9-481f-a5e6-0a88ab0b7960', 'KANBAN_SYSTEM', 1, 25);

INSERT INTO task (task_id, room_id, is_blocked, kanban_column, start_day,
                  end_day, visible_from_day, effort, name, type, due_day)
VALUES ('c7a5fd59-90ae-45f1-a62e-702714179e19', '0910ab8a-27d9-481f-a5e6-0a88ab0b7960', true, 'STAGE_ONE_IN_PROGRESS',
        null,
        null, null, null, 'S.1', 'STANDARD', null);

INSERT INTO room_member (room_member_id, name, color, type, room_id, daily_productivity, unblocked_tasks_productivity)
VALUES ('a08f011d-2721-4e6e-9867-9bea84b91542', 'Jan Nowak', 'RED', 'PARTICIPANT',
        '0910ab8a-27d9-481f-a5e6-0a88ab0b7960',
        'null,null,null,null,null,null,null,null,null,null', 'null,null,null,null,null,null,null,null,null,null');

INSERT INTO room_member (room_member_id, name, color, type, room_id, daily_productivity, unblocked_tasks_productivity)
VALUES ('0982d7ac-74fa-483b-acf2-cd70d97c10e5', 'Kasia Nowak', 'PINK', 'PARTICIPANT',
        '0910ab8a-27d9-481f-a5e6-0a88ab0b7960',
        'null,null,null,null,null,null,null,null,null,null', 'null,null,null,null,null,null,null,null,null,null')


