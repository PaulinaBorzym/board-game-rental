package com.project.service;

import com.project.domain.Game;
import com.project.domain.User;
import com.project.exeption.GameNotFoundException;
import com.project.exeption.UserNotFoundException;
import com.project.repository.GameRepository;
import com.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUser(final Long userId) throws UserNotFoundException {
        return repository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(final User user){
        return repository.save(user);
    }

    public void deleteUser(final Long userId){
        repository.deleteById(userId);
    }
}