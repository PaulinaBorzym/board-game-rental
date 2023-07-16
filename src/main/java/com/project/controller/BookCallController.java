package com.project.controller;

import com.project.domain.BookCall;
import com.project.domain.Game;
import com.project.domain.Rent;
import com.project.dto.BookCallDto;
import com.project.dto.GameDto;
import com.project.dto.RentDto;
import com.project.enums.LogType;
import com.project.mapper.BookCallMapper;
import com.project.service.BookCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/book/call")
public class BookCallController {
    private final BookCallService service;
    private final BookCallMapper bookCallMapper;

    @GetMapping
    public ResponseEntity<List<BookCallDto>> getBookCall(){
        List<BookCall> bookCalls = service.getAllBookCall();
        return ResponseEntity.ok(bookCallMapper.mapToBookCallDtoList(bookCalls));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBookCall(@RequestBody BookCallDto bookCallDto) {
        BookCall bookCall = bookCallMapper.mapToBookCall(bookCallDto);
        service.saveBookCall(bookCall);
        return ResponseEntity.ok().build();
    }
}
