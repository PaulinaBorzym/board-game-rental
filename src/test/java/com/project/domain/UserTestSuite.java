package com.project.domain;

import com.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTestSuite {
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateAndDeleteUser() {
        //Given
        User user = new User("Ania", "Kania", "kania@ania", "55555");

        //When
        Long numberOfUsers = userRepository.count();
        userRepository.save(user);

        //Then
        assertEquals(numberOfUsers + 1, userRepository.count());
        userRepository.deleteById(user.getUserId());
        assertEquals(numberOfUsers, userRepository.count());
    }

    @Test
    void shouldFindAllUsers() {
        //Given
        List<User> userList = new ArrayList<>();
        User user = new User("Ania", "Kania", "kania@ania", "55555");
        User user2 = new User("Hania", "Bania", "bania@hania", "465613");
        userList.add(user);
        userList.add(user2);

        //When
        Long numberOfUsers = userRepository.count();
        userRepository.save(user);
        userRepository.save(user2);

        //Then
        assertEquals(numberOfUsers + 2, userRepository.count());

        //CleanUp
        userRepository.deleteById(user.getUserId());
        userRepository.deleteById(user2.getUserId());
    }
}