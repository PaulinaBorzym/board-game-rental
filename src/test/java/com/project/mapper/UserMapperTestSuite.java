package com.project.mapper;

import com.project.domain.User;
import com.project.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserMapperTestSuite {
    private UserMapper userMapper = new UserMapper();

    @Test
    public void shouldMapUserDtoToUser() {
        // Given
        UserDto userDto = new UserDto(1L, "Ania", "Kania", "kania@ania",
                "55555");

        // When
        User user = userMapper.mapToUser(userDto);

        // Then
        assertEquals(userDto.getUserId(), user.getUserId());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPhoneNumber(), user.getPhoneNumber());
    }

    @Test
    public void shouldMapUserToUserDto() {
        // Given
        User user = new User(1L, "Ania", "Kania", "kania@ania", "55555");

        // When
        UserDto userDto = userMapper.mapToUserDto(user);

        // Then
        assertEquals(user.getUserId(), userDto.getUserId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPhoneNumber(), userDto.getPhoneNumber());
    }

    @Test
    public void shouldMapListOfUsersToListOfUsersDto() {
        // Given
        List<User> userList = Arrays.asList(
                new User(1L, "Ania", "Kania", "kania@ania", "55555"),
                new User(2L, "Hania", "Bania", "bania@hania", "465613"));

        // When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);

        // Then
        assertEquals(userList.size(), userDtoList.size());
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            UserDto userDto = userDtoList.get(i);

            assertEquals(user.getUserId(), userDto.getUserId());
            assertEquals(user.getFirstName(), userDto.getFirstName());
            assertEquals(user.getLastName(), userDto.getLastName());
            assertEquals(user.getEmail(), userDto.getEmail());
            assertEquals(user.getPhoneNumber(), userDto.getPhoneNumber());
        }
    }
}