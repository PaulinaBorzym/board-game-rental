package com.project.service;

import com.project.controller.StatisticController;
import com.project.domain.MonthStatistic;
import com.project.repository.MonthStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MonthStatisticService {
    MonthStatisticRepository repository;
    StatisticController controller;

    @Autowired
    public MonthStatisticService(MonthStatisticRepository repository,StatisticController controller) {
        this.repository = repository;
        this.controller = controller;
    }

    public List<MonthStatistic> getAllMonthStatistic(){
        return repository.findAll();
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public MonthStatistic saveMonthStatistic(){
        MonthStatistic statistic = new MonthStatistic(
                LocalDate.now().getMonth().name(),
                String.valueOf(LocalDate.now().getYear()),
                controller.getNumberOfUsers().getBody(),
                controller.getNumberOfGames().getBody(),
                controller.getNumberOfAllRents().getBody(),
                controller.getNumberOfLastMonthRents().getBody(),
                controller.getAmountOfLastMonthEarnedMoney().getBody()
                );

        return repository.save(statistic);
    }
}
