package com.project.mapper;

import com.project.domain.BookCall;
import com.project.dto.BookCallDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCallMapper {
    public BookCall mapToBookCall(final BookCallDto bookCallDto) {
        return new BookCall(
                bookCallDto.getBookCallId(),
                bookCallDto.getBookDate(),
                bookCallDto.getPhoneNumber(),
                bookCallDto.getTitle()
        );
    }

    public BookCallDto mapToBookCallDto(final BookCall bookCall) {
        return BookCallDto.builder()
                .bookCallId(bookCall.getBookCallId())
                .bookDate(bookCall.getBookDate())
                .phoneNumber(bookCall.getPhoneNumber())
                .title(bookCall.getTitle())
                .build();
    }

    public List<BookCallDto> mapToBookCallDtoList(final List<BookCall> bookCallList) {
        return bookCallList.stream()
                .map(this::mapToBookCallDto)
                .collect(Collectors.toList());
    }
}