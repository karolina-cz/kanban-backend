package com.pw.kanban.application.column_limit;

import com.pw.kanban.domain.column_limit.ColumnLimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteColumnLimitHandler {
    private final ColumnLimitRepository columnLimitRepository;

    @Transactional
    public void handle(UUID columnLimitId) {
        columnLimitRepository.deleteById(columnLimitId);
    }
}
