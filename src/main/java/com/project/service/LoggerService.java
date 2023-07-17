package com.project.service;

import com.project.domain.*;
import com.project.enums.LogType;
import com.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class LoggerService {
    private GameLogsRepository gameLogsRepository;
    private UserLogsRepository userLogsRepository;
    private RentLogsRepository rentLogsRepository;
    private CurrencyLogsRepository currencyLogsRepository;
    private GameRepository gameRepository;
    private UserRepository userRepository;
    private RentRepository rentRepository;
    private CurrencyRepository currencyRepository;

    @Autowired
    public LoggerService(GameLogsRepository gameLogsRepository, UserLogsRepository userLogsRepository,
                         RentLogsRepository rentLogsRepository, CurrencyLogsRepository currencyLogsRepository,
                         GameRepository gameRepository, UserRepository userRepository, RentRepository rentRepository,
                         CurrencyRepository currencyRepository) {
        this.gameLogsRepository = gameLogsRepository;
        this.userLogsRepository = userLogsRepository;
        this.rentLogsRepository = rentLogsRepository;
        this.currencyLogsRepository = currencyLogsRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
        this.currencyRepository = currencyRepository;
    }

    public void saveGameLog(LogType type, Game game) {
        GameLogs log = new GameLogs();
        log.setTimeStamp(LocalDateTime.now());
        if (type.equals(LogType.CREATE)) {
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
        if (Objects.nonNull(log.getTypeOfOperation())) {
            gameLogsRepository.save(log);
        }
    }

    public void saveRentLog(LogType type, Rent rent) {
        RentLogs log = new RentLogs();
        log.setTimeStamp(LocalDateTime.now());
        if (type.equals(LogType.CREATE)) {
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
        if (Objects.nonNull(log.getTypeOfOperation())) {
            rentLogsRepository.save(log);
        }
    }

    public void saveUserLog(LogType type, User user) {
        UserLogs log = new UserLogs();
        log.setTimeStamp(LocalDateTime.now());
        if (type.equals(LogType.CREATE)) {
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
        if (Objects.nonNull(log.getTypeOfOperation())) {
            userLogsRepository.save(log);
        }
    }

    @Scheduled(cron = "0 10 1 * * *")
    public void saveCurrencyLog() {
        CurrencyLogs log = new CurrencyLogs();
        log.setTimeStamp(LocalDateTime.now());
        List<Currency> currencyList = currencyRepository.findAll();
        log.setPLN(findValue("PLN", currencyList));
        log.setUSD(findValue("USD", currencyList));
        log.setEUR(findValue("EUR", currencyList));
        currencyLogsRepository.save(log);
    }

    private double findValue(String currencyCode, List<Currency> currencyList) {
        return currencyList.stream()
                .filter(currency -> currency.getCurrencyCode().equals(currencyCode))
                .map(currency -> currency.getValue())
                .findFirst()
                .orElse(null);
    }
}
