package com.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.domain.User;
import com.project.dto.UserDto;
import com.project.exeption.UserNotFoundException;
import com.project.mapper.UserMapper;
import com.project.service.LoggerService;
import com.project.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private LoggerService loggerService;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Ania", "Kania", "kania@ania", "55555");
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        // Given
        List<User> users = Arrays.asList(user);
        when(userService.getAllUsers()).thenReturn(users);

        UserDto userDto = new UserDto(1L, "Ania", "Kania", "kania@ania",
                "55555");
        when(userMapper.mapToUserDtoList(any())).thenReturn(Arrays.asList(userDto));

        // When&Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName").value("Ania"))
                .andExpect(jsonPath("$[0].lastName").value("Kania"));
    }

    @Test
    void shouldGetUserById() throws Exception, UserNotFoundException {
        // Given
        when(userService.getUser(1L)).thenReturn(user);

        UserDto userDto = new UserDto(1L, "Ania", "Kania", "kania@ania",
                "55555");
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        // When&Then
        mockMvc.perform(get("/v1/users/{userId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Ania"))
                .andExpect(jsonPath("$.lastName").value("Kania"));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        // Given
        UserDto userDto = new UserDto(1L, "Ania", "Kania", "kania@ania",
                "55555");
        when(userMapper.mapToUser(any())).thenReturn(user);

        User savedUser = new User(2L, "Hania", "Bania", "bania@hania",
                "465613");
        when(userService.saveUser(user)).thenReturn(savedUser);

        UserDto savedUserDto = new UserDto(2L, "Hania", "Bania", "bania@hania",
                "465613");
        when(userMapper.mapToUserDto(savedUser)).thenReturn(savedUserDto);

        // When&Then
        mockMvc.perform(put("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Hania"))
                .andExpect(jsonPath("$.lastName").value("Bania"));
    }
}