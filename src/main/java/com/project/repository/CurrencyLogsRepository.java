package com.project.repository;

import com.project.domain.CurrencyLogs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CurrencyLogsRepository extends CrudRepository<CurrencyLogs, Long> {
    @Override
    CurrencyLogs save(CurrencyLogs currencyLogs);
}
