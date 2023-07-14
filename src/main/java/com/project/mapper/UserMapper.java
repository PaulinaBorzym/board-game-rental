package com.project.mapper;

import com.project.domain.User;
import com.project.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPhoneNumber()
        );
    }
    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }
    public List<UserDto> mapToUserDtoList (final List<User> userList){
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
