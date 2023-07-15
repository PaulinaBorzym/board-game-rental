package com.project.controller;

import com.project.domain.User;
import com.project.dto.UserDto;
import com.project.enums.LogType;
import com.project.exeption.UserNotFoundException;
import com.project.mapper.UserMapper;
import com.project.service.LoggerService;
import com.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserController {

    private final UserService service;
    private final LoggerService logger;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>>getUsers(){
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
       return ResponseEntity.ok(userMapper.mapToUserDto(service.getUser(userId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        service.saveUser(user);
        logger.saveUserLog(LogType.CREATE,user);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        logger.saveUserLog(LogType.UPDATE,user);
        User savedUser = service.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        logger.saveUserLog(LogType.DELETE,service.getUser(userId));
        service.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}
