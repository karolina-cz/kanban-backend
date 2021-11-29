INSERT INTO simulation (simulation_id, simulation_type) VALUES ('14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 'KANBAN_SYSTEM');
INSERT INTO simulation_day (simulation_day_id, simulation_id, day_number, narrative) VALUES
('b5653b25-c995-4721-8107-c091b8ea0359', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 1, 'Rozpoczynacie pracę w nowym zespole.' ||
                                  ' Zostały dla was zdefiniowane zadania standardowe. Zacznijcie nad nimi pracować.'),
('a5d5822a-a457-41b2-86e5-c685afe26757', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 2, 'Dobra robota! Pracujcie dalej.'),
('209c7937-3a27-4f59-96a0-0fed9e46b4df', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 3, 'System płatności uległ awarii. Z każdą godziną firma traci duże pieniądze.' ||
                                  ' Zdefiniowane zostały 3 zadania pilne (Urgent). Natychmiast zacznijcie nad nimi pracować.' ||
                                  ' Ich ukończenie ma najwyższy priorytet.'),
('8f40decd-cfca-4ed7-ab4e-72ec3e9ec55d', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 4, 'Specjaliści od marketingu wymyślili promocję z okazji „Dnia Słonecznika”.' ||
                                  'Stworzone zostały dla was 3 zadania z ustaloną datą (Fixed Date). ' ||
                                  'By przynieść wartość dla naszej organizacji muszą być ukończone najpóźniej 10 dnia. ' ||
                                  'Ich wcześniejsze ukończenie nie jest ważne bo i tak promocja nie zostanie upubliczniona do końca 10 dnia.'),
('60451081-1c9b-4044-b596-ac0f4076f1db', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 5, 'To ostatni dzień w tygodniu. Nie zepsujcie produkcji, bo przed nami weekend. Miłego wypoczynku!'),
('92c04fb6-62ef-45b9-bfb7-3092b341af3c', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 6, 'Nowy tydzień, nowe możliwości. Powodzenia.'),
('f0a22538-236a-41cf-882c-445a7faad350', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 7, 'Dobra robota, pracujcie dalej.'),
('0f90eefe-2ad2-4fcf-bca3-9b2fae5b9ebf', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 8, 'Jeden z menedżerów wpadł na pomysł dodania nowej funkcji do naszego produktu. ' ||
                                  'Wymagałoby to zrealizowania 4 standardowych zadań ' ||
                                  '(wszystkie muszą być ukończone by nowa funkcja działała). ' ||
                                  'Prosi was o podanie szacowanego czasu zrealizowania tych zadań. ' ||
                                  'Szacowany dzień ukończenia tych 4 zadań zapiszcie w skrypcie.'),
('00128dfa-dcbe-4328-8852-4bf4f863b8f7', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 9, 'Zostały już tylko dwa dni na ukończenie zadań z ustaloną datą.'),
('25f51d18-cbfe-428d-8a9b-c3b971f5202e', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 10, 'Gratulacje! Dotrwaliście do ostatniego dnia symulacji. ' ||
                                   'Dokończcie pracę i wybierzcie się na zasłużony urlop!');
INSERT INTO task_template (task_template_id, simulation_id, tasks_number, tasks_type, visible_from_day, due_day) VALUES
('1592a4ab-ad4b-420c-9bb6-74dfac063c1b', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 3, 'URGENT', 3, null),
('871c8354-e07d-4b5f-b839-e984ec362646', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 3, 'FIXED_DATE', 4, 10),
('0656cbd0-c839-45fa-9646-43dc2e5a8479', '14ce82ba-fb89-483f-b1e1-7d5ae5b174a5', 6, 'STANDARD', null, null);


INSERT INTO simulation (simulation_id, simulation_type) VALUES ('57ef30e4-bf27-422a-b308-95fa1c306985', 'KANBAN_BOARD');
INSERT INTO simulation_day (simulation_day_id, simulation_id, day_number, narrative) VALUES
('be77cb2c-e704-4198-9038-0cac545f8031', '57ef30e4-bf27-422a-b308-95fa1c306985', 1, 'Rozpoczynacie pracę w nowym zespole.' ||
                                    ' Zostały dla was zdefiniowane zadania standardowe. Zacznijcie nad nimi pracować.'),
('1ff63b8e-230a-4a05-aef0-7d0385aa7f2c', '57ef30e4-bf27-422a-b308-95fa1c306985', 2, 'Dobra robota! Pracujcie dalej.'),
('675ff52b-d7af-4a2f-b278-e75e6e297f31', '57ef30e4-bf27-422a-b308-95fa1c306985', 3, 'System płatności uległ awarii. Z każdą godziną firma traci duże pieniądze.' ||
                                    ' Zdefiniowane zostały 3 zadania pilne (Urgent). Natychmiast zacznijcie nad nimi pracować.' ||
                                    ' Ich ukończenie ma najwyższy priorytet.'),
('9f3dd4e6-7022-4951-914b-b9474c3ccb49', '57ef30e4-bf27-422a-b308-95fa1c306985', 4, 'Specjaliści od marketingu wymyślili promocję z okazji „Dnia Słonecznika”.' ||
                                    'Stworzone zostały dla was 3 zadania z ustaloną datą (Fixed Date). ' ||
                                    'By przynieść wartość dla naszej organizacji muszą być ukończone najpóźniej 10 dnia. ' ||
                                    'Ich wcześniejsze ukończenie nie jest ważne bo i tak promocja nie zostanie upubliczniona do końca 10 dnia.'),
('fe59893a-356a-45fd-a57a-fe29a4278683', '57ef30e4-bf27-422a-b308-95fa1c306985', 5, 'To ostatni dzień w tygodniu. Nie zepsujcie produkcji, bo przed nami weekend. Miłego wypoczynku!'),
('24984a35-b3fe-4ee4-92f6-cf3e62d7571a', '57ef30e4-bf27-422a-b308-95fa1c306985', 6, 'Nowy tydzień, nowe możliwości. Powodzenia.'),
('7b8e2a7d-6de3-43fc-8b83-59a48ee7a731', '57ef30e4-bf27-422a-b308-95fa1c306985', 7, 'Dobra robota, pracujcie dalej.'),
('c761cbba-f634-4a40-a10a-267e6b460a2f', '57ef30e4-bf27-422a-b308-95fa1c306985', 8, 'Jeden z menedżerów wpadł na pomysł dodania nowej funkcji do naszego produktu. ' ||
                                    'Wymagałoby to zrealizowania 4 standardowych zadań ' ||
                                    '(wszystkie muszą być ukończone by nowa funkcja działała). ' ||
                                    'Prosi was o podanie szacowanego czasu zrealizowania tych zadań. ' ||
                                    'Szacowany dzień ukończenia tych 4 zadań zapiszcie w skrypcie.'),
('0f669e5c-a35c-4a01-911f-86f1ced3d4a1', '57ef30e4-bf27-422a-b308-95fa1c306985', 9, 'Zostały już tylko dwa dni na ukończenie zadań z ustaloną datą.'),
('4ffc79cc-4afa-48f9-b122-adaa2e9960f8', '57ef30e4-bf27-422a-b308-95fa1c306985', 10, 'Gratulacje! Dotrwaliście do ostatniego dnia symulacji. ' ||
                                     'Dokończcie pracę i wybierzcie się na zasłużony urlop!');
INSERT INTO task_template (task_template_id, simulation_id, tasks_number, tasks_type, visible_from_day, due_day) VALUES
('c7beb41b-2bf1-46a1-bc28-fc93b6aeae4d', '57ef30e4-bf27-422a-b308-95fa1c306985', 3, 'URGENT', 3, null),
('a4af248e-5fdc-42bf-ae0f-312cd3dd2b76', '57ef30e4-bf27-422a-b308-95fa1c306985', 3, 'FIXED_DATE', 4, 10),
('a2c14afe-87a1-47b5-b5c1-60791f3394b0', '57ef30e4-bf27-422a-b308-95fa1c306985', 6, 'STANDARD', null, null);
