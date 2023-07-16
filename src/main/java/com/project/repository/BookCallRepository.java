package com.project.repository;

import com.project.domain.BookCall;
import com.project.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BookCallRepository  extends CrudRepository<BookCall, Long> {

    @Override
    List<BookCall> findAll();

    @Override
    BookCall save(BookCall bookCall);
}
