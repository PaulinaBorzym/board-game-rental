package com.project.repository;

import com.project.domain.Rent;
import com.project.domain.UserLogs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserLogsRepository extends CrudRepository<UserLogs,Long> {
    @Override
    UserLogs save(UserLogs userLogs);
}
