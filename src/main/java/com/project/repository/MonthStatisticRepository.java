package com.project.repository;

import com.project.domain.MonthStatistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MonthStatisticRepository extends CrudRepository<MonthStatistic, Long> {
    @Override
    List<MonthStatistic> findAll();

    @Override
    MonthStatistic save(MonthStatistic monthStatistic);
}