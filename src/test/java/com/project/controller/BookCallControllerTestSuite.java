package com.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.domain.BookCall;
import com.project.dto.BookCallDto;
import com.project.mapper.BookCallMapper;
import com.project.service.BookCallService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class BookCallControllerTestSuite {
    private MockMvc mockMvc;
    @Mock
    private BookCallService bookCallService;
    @Mock
    private BookCallMapper bookCallMapper;
    @InjectMocks
    private BookCallController bookCallController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookCallController).build();
    }

    @Test
    public void shouldReturnListOfBookCalls() throws Exception {
        // Given
        List<BookCall> bookCalls = Arrays.asList(new BookCall(), new BookCall());
        List<BookCallDto> bookCallsDto = Arrays.asList(new BookCallDto(), new BookCallDto());

        when(bookCallService.getAllBookCall()).thenReturn(bookCalls);
        when(bookCallMapper.mapToBookCallDtoList(bookCalls)).thenReturn(bookCallsDto);

        // When&Then
        mockMvc.perform(get("/v1/book/call")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(bookCalls.size()));
    }

    @Test
    public void shouldCreatedBookCall() throws Exception {
        // Given
        BookCallDto bookCallDto = new BookCallDto();
        BookCall bookCall = new BookCall();
        ObjectMapper objectMapper = new ObjectMapper();

        when(bookCallMapper.mapToBookCall(bookCallDto)).thenReturn(bookCall);

        // When&Then
        mockMvc.perform(post("/v1/book/call")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookCallDto)))
                .andExpect(status().isOk());
    }
}