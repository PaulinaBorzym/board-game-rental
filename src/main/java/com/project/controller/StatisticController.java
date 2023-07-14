package com.project.controller;

import com.project.domain.Game;
import com.project.domain.Rent;
import com.project.domain.User;
import com.project.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/statistic")
public class StatisticController {

    private final StatisticService service;

    @GetMapping(value = "/numberOfUsers", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Integer> getNumberOfUsers(){
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users.size());
    }

    @GetMapping(value = "/numberOfGames", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Integer> getNumberOfGames(){
        List<Game> games = service.getAllGames();
        return ResponseEntity.ok(games.size());
    }

    @GetMapping(value = "/numberOfAllRents", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Integer> getNumberOfAllRents(){
        List<Rent> rents = service.getAllRents();
        return ResponseEntity.ok(rents.size());
    }

    @GetMapping(value = "/numberOfLastMonthRents", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Integer> getNumberOfLastMonthRents() {
        List<Rent> rents = service.getAllRents();
        List<Rent> lastMonthRents = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        int currentMonthValue = currentDate.getMonthValue();
        for (Rent rent : rents) {
            LocalDate rentDate = rent.getStartDate();
            int rentMonthValue = rentDate.getMonthValue();
            if (rentMonthValue == currentMonthValue - 1) {
                lastMonthRents.add(rent);
            }
        }
            return ResponseEntity.ok(lastMonthRents.size());
    }

    @GetMapping(value = "/numberOfLastWeekRents", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Integer> getNumberOfLastWeekRents(){
        List<Rent> rents = service.getAllRents();
        List<Rent> lastMonthRents = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate lastWeekStartDate = currentDate.minus(1, ChronoUnit.WEEKS);
        LocalDate lastWeekEndDate = currentDate.minus(1, ChronoUnit.DAYS);

        for (Rent rent : rents) {
            LocalDate rentDate = rent.getStartDate();
            if (rentDate.isAfter(lastWeekStartDate) && rentDate.isBefore(lastWeekEndDate.plusDays(1))) {
                lastMonthRents.add(rent);
            }
        }
        return ResponseEntity.ok(lastMonthRents.size());
    }

    @GetMapping(value = "/numberOfAllEarnedMoney", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Double> getAmountOfAllEarnedMoney(){
        List<Rent> rents = service.getAllRents();
        double sum = 0.0;
        for(Rent rent : rents) {
            sum += rent.getPrice();
        }
        return ResponseEntity.ok(sum);
    }

    @GetMapping(value = "/numberOfLastMonthEarnedMoney", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Double> getAmountOfLastMonthEarnedMoney(){
        List<Rent> rents = service.getAllRents();
        double sum = 0.0;
        LocalDate currentDate = LocalDate.now();
        int currentMonthValue = currentDate.getMonthValue();

        for (Rent rent : rents){
            LocalDate rentDate = rent.getStartDate();
            int rentMonthValue = rentDate.getMonthValue();
            if (rentMonthValue == currentMonthValue - 1) {
                sum += rent.getPrice();
            }
        }
        return ResponseEntity.ok(sum);
    }

    @GetMapping(value = "/numberOfLastWeekEarnedMoney", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Double> getAmountOfLastWeekEarnedMoney(){
        List<Rent> rents = service.getAllRents();
        double sum = 0.0;
        LocalDate currentDate = LocalDate.now();
        LocalDate lastWeekStartDate = currentDate.minus(1, ChronoUnit.WEEKS);
        LocalDate lastWeekEndDate = currentDate.minus(1, ChronoUnit.DAYS);

        for (Rent rent : rents) {
            LocalDate rentDate = rent.getStartDate();
            if (rentDate.isAfter(lastWeekStartDate) && rentDate.isBefore(lastWeekEndDate.plusDays(1))) {
                sum += rent.getPrice();
            }
        }
        return ResponseEntity.ok(sum);
    }
}
