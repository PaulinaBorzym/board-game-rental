package com.project.service;

import com.project.domain.*;
import com.project.enums.LogType;
import com.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class LoggerService {
    private GameLogsRepository gameLogsRepository;
    private UserLogsRepository userLogsRepository;
    private RentLogsRepository rentLogsRepository;
    private GameRepository gameRepository;
    private UserRepository userRepository;
    private RentRepository rentRepository;

    @Autowired
    public LoggerService(GameLogsRepository gameLogsRepository, UserLogsRepository userLogsRepository,
                         RentLogsRepository rentLogsRepository, GameRepository gameRepository,
                         UserRepository userRepository, RentRepository rentRepository) {
        this.gameLogsRepository = gameLogsRepository;
        this.userLogsRepository = userLogsRepository;
        this.rentLogsRepository = rentLogsRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
    }

    public void saveGameLog(LogType type, Game game){
        GameLogs log = new GameLogs();
        log.setTimeStamp(LocalDateTime.now());
        if(type.equals(LogType.CREATE)){
            log.setNewValue(game.toString());
            log.setTypeOfOperation(type.toString());
        } else if (type.equals(LogType.DELETE)) {
            log.setPreviousValue(game.toString());
            log.setTypeOfOperation(type.toString());
        } else if (type.equals(LogType.UPDATE)) {
            log.setNewValue(game.toString());
            log.setPreviousValue(gameRepository.findById(game.getGameId()).get().toString());
            log.setTypeOfOperation(type.toString());
        }
        if (Objects.nonNull(log.getTypeOfOperation())){
            gameLogsRepository.save(log);
        }
    }
    public void saveRentLog(LogType type, Rent rent){
        RentLogs log = new RentLogs();
        log.setTimeStamp(LocalDateTime.now());
        if(type.equals(LogType.CREATE)){
            log.setNewValue(rent.toString());
            log.setTypeOfOperation(type.toString());
        } else if (type.equals(LogType.DELETE)) {
            log.setPreviousValue(rent.toString());
            log.setTypeOfOperation(type.toString());
        } else if (type.equals(LogType.UPDATE)) {
            log.setNewValue(rent.toString());
            log.setPreviousValue(rentRepository.findById(rent.getRentId()).get().toString());
            log.setTypeOfOperation(type.toString());
        }
        if (Objects.nonNull(log.getTypeOfOperation())){
            rentLogsRepository.save(log);
        }

    }

    public void saveUserLog(LogType type, User user){
        UserLogs log = new UserLogs();
        log.setTimeStamp(LocalDateTime.now());
        if(type.equals(LogType.CREATE)){
            log.setNewValue(user.toString());
            log.setTypeOfOperation(type.toString());
        } else if (type.equals(LogType.DELETE)) {
            log.setPreviousValue(user.toString());
            log.setTypeOfOperation(type.toString());
        } else if (type.equals(LogType.UPDATE)) {
            log.setNewValue(user.toString());
            log.setPreviousValue(userRepository.findById(user.getUserId()).get().toString());
            log.setTypeOfOperation(type.toString());
        }
        if (Objects.nonNull(log.getTypeOfOperation())){
            userLogsRepository.save(log);
        }
    }
}
