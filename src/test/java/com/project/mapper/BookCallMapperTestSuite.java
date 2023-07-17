package com.project.mapper;

import com.project.domain.BookCall;
import com.project.dto.BookCallDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookCallMapperTestSuite {
    private BookCallMapper bookCallMapper = new BookCallMapper();

    @Test
    public void shouldMapBookCallDtoToBookCall() {
        // Given
        BookCallDto bookCallDto = new BookCallDto(1L, LocalDate.of(2023, 7, 20),
                "78945", "Kalambury");

        // When
        BookCall bookCall = bookCallMapper.mapToBookCall(bookCallDto);

        // Then
        assertEquals(bookCallDto.getBookCallId(), bookCall.getBookCallId());
        assertEquals(bookCallDto.getBookDate(), bookCall.getBookDate());
        assertEquals(bookCallDto.getPhoneNumber(), bookCall.getPhoneNumber());
        assertEquals(bookCallDto.getTitle(), bookCall.getTitle());
    }

    @Test
    public void shouldMapBookCallToBookCallDto() {
        // Given
        BookCall bookCall = new BookCall(1L, LocalDate.of(2023, 7, 20),
                "78945", "Kalambury");

        // When
        BookCallDto bookCallDto = bookCallMapper.mapToBookCallDto(bookCall);

        // Then
        assertEquals(bookCall.getBookCallId(), bookCallDto.getBookCallId());
        assertEquals(bookCall.getBookDate(), bookCallDto.getBookDate());
        assertEquals(bookCall.getPhoneNumber(), bookCallDto.getPhoneNumber());
        assertEquals(bookCall.getTitle(), bookCallDto.getTitle());
    }

    @Test
    public void shouldMapListOfBookCallsToListOfBookCallsDto() {
        // Given
        BookCall bookCall1 = new BookCall(1L, LocalDate.of(2023, 7, 20),
                "78945", "Kalambury");
        BookCall bookCall2 = new BookCall(2L, LocalDate.of(2023, 7, 21),
                "12345", "Kalambury");
        List<BookCall> bookCallList = Arrays.asList(bookCall1, bookCall2);

        // When
        List<BookCallDto> bookCallDtoList = bookCallMapper.mapToBookCallDtoList(bookCallList);

        // Then
        assertEquals(bookCallList.size(), bookCallDtoList.size());
        for (int i = 0; i < bookCallList.size(); i++) {
            BookCall bookCall = bookCallList.get(i);
            BookCallDto bookCallDto = bookCallDtoList.get(i);

            assertEquals(bookCall.getBookCallId(), bookCallDto.getBookCallId());
            assertEquals(bookCall.getBookDate(), bookCallDto.getBookDate());
            assertEquals(bookCall.getPhoneNumber(), bookCallDto.getPhoneNumber());
            assertEquals(bookCall.getTitle(), bookCallDto.getTitle());
        }
    }
}