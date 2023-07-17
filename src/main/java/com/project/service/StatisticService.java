package com.project.service;

import com.project.domain.Game;
import com.project.domain.Rent;
import com.project.domain.User;
import com.project.repository.GameRepository;
import com.project.repository.RentRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    GameRepository gameRepository;
    RentRepository rentRepository;
    UserRepository userRepository;

    @Autowired
    public StatisticService(GameRepository gameRepository, RentRepository rentRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

