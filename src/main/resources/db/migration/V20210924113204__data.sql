DO $$
    DECLARE simId uuid;
    BEGIN
        INSERT INTO simulation (simulation_id) VALUES (gen_random_uuid ()) RETURNING simulation_id INTO simId;
        INSERT INTO simulation_day (simulation_day_id, simulation_id, day_number, narrative) VALUES
            (gen_random_uuid (), simId, 1, 'Rozpoczynacie pracę w nowym zespole.' ||
                                                      ' Zostały dla was zdefiniowane zadania standardowe. Zacznijcie nad nimi pracować.'),
            (gen_random_uuid (), simId, 2, 'Dobra robota! Pracujcie dalej.'),
            (gen_random_uuid (), simId, 3, 'System płatności uległ awarii. Z każdą godziną firma traci duże pieniądze.' ||
                                                      ' Zdefiniowane zostały 4 zadania pilne (Urgent). Natychmiast zacznijcie nad nimi pracować.' ||
                                                      ' Ich ukończenie ma najwyższy priorytet.'),
            (gen_random_uuid (), simId, 4, 'Specjaliści od marketingu wymyślili promocję z okazji „Dnia Słonecznika”.' ||
                                                      'Stworzone zostały dla was 3 zadania z ustaloną datą (Fixed Date). ' ||
                                                      'By przynieść wartość dla naszej organizacji muszą być ukończone najpóźniej 10 dnia. ' ||
                                                      'Ich wcześniejsze ukończenie nie jest ważne bo i tak promocja nie zostanie upubliczniona do końca 10 dnia.'),
            (gen_random_uuid (), simId, 5, 'To ostatni dzień w tygodniu. Nie zepsujcie produkcji, bo przed nami weekend. Miłego wypoczynku!'),
            (gen_random_uuid (), simId, 6, 'Nowy tydzień, nowe możliwości. Powodzenia.'),
            (gen_random_uuid (), simId, 7, 'Dobra robota, pracujcie dalej.'),
            (gen_random_uuid (), simId, 8, 'Jeden z menedżerów wpadł na pomysł dodania nowej funkcji do naszego produktu. ' ||
                                                      'Wymagałoby to zrealizowania 4 standardowych zadań ' ||
                                                      '(wszystkie muszą być ukończone by nowa funkcja działała). ' ||
                                                      'Prosi was o podanie szacowanego czasu zrealizowania tych zadań. ' ||
                                                      'Szacowany dzień ukończenia tych 4 zadań zapiszcie w polach "koniec".'),
            (gen_random_uuid (), simId, 9, 'Zostały już tylko dwa dni na ukończenie zadań z ustaloną datą.'),
            (gen_random_uuid (), simId, 10, 'Gratulacje! Dotrwaliście do ostatniego dnia symulacji. ' ||
                                                       'Dokończcie pracę i wybierzcie się na zasłużony urlop!');
        INSERT INTO task_template (task_template_id, simulation_id, tasks_number, tasks_type, visible_from_day, due_day) VALUES
            (gen_random_uuid (), simId, 4, 'URGENT', 3, null),
            (gen_random_uuid (), simId, 3, 'FIXED_DATE', 4, 10),
            (gen_random_uuid (), simId, 6, 'STANDARD', null, null);
END $$;
