package com.project.repository;

import com.project.domain.RentLogs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RentLogsRepository extends CrudRepository<RentLogs, Long> {
    @Override
    RentLogs save(RentLogs rentLogs);
}
