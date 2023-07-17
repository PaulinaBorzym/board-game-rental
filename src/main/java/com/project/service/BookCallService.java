package com.project.service;

import com.project.domain.BookCall;
import com.project.repository.BookCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCallService {
    private BookCallRepository repository;

    @Autowired
    public BookCallService(BookCallRepository repository) {
        this.repository = repository;
    }

    public List<BookCall> getAllBookCall() {
        return repository.findAll();
    }

    public BookCall saveBookCall(final BookCall bookCall) {
        return repository.save(bookCall);
    }
}
