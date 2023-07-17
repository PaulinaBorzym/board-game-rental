package com.project.repository;

import com.project.domain.GameLogs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GameLogsRepository extends CrudRepository<GameLogs, Long> {
    @Override
    GameLogs save(GameLogs gameLogs);
}
