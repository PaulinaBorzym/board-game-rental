package com.project.controller;

import com.project.domain.Game;
import com.project.domain.Rent;
import com.project.domain.User;
import com.project.service.StatisticService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticControllerTestSuite {
    @Mock
    private StatisticService statisticService;
    @InjectMocks
    private StatisticController statisticController;

    @Test
    public void shouldReturnNumberOfUsers() {
        // Given
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(statisticService.getAllUsers()).thenReturn(users);
        // When
        ResponseEntity<Integer> responseEntity = statisticController.getNumberOfUsers();

        // Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(2);
    }

    @Test
    public void shouldReturnNumberOfGames() {
        // Given
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        games.add(new Game());
        when(statisticService.getAllGames()).thenReturn(games);

        // When
        ResponseEntity<Integer> responseEntity = statisticController.getNumberOfGames();

        // Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(2);
    }

    @Test
    public void shouldReturnNumberOfAllRents() {
        // Given
        List<Rent> rents = new ArrayList<>();
        rents.add(new Rent());
        rents.add(new Rent());
        when(statisticService.getAllRents()).thenReturn(rents);

        // When
        ResponseEntity<Integer> responseEntity = statisticController.getNumberOfAllRents();

        // Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(2);
    }

    @Test
    public void shouldReturnNumberOfLastMonthRents() {
        // Given
        List<Rent> rents = new ArrayList<>();
        rents.add(createRent(LocalDate.now().minusMonths(1)));
        rents.add(createRent(LocalDate.now().minusMonths(1)));
        rents.add(createRent(LocalDate.now()));
        when(statisticService.getAllRents()).thenReturn(rents);

        // When
        ResponseEntity<Integer> responseEntity = statisticController.getNumberOfLastMonthRents();

        // Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(2);
    }

    @Test
    public void shouldReturnNumberOfLastWeekRents() {
        // Given
        List<Rent> rents = new ArrayList<>();
        rents.add(createRent(LocalDate.now().minusWeeks(1)));
        rents.add(createRent(LocalDate.now().minusWeeks(1)));
        rents.add(createRent(LocalDate.now()));
        when(statisticService.getAllRents()).thenReturn(rents);

        // When
        ResponseEntity<Integer> responseEntity = statisticController.getNumberOfLastWeekRents();

        // Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(2);
    }

    @Test
    public void shouldReturnAmountOfAllEarnedMoney() {
        // Given
        List<Rent> rents = new ArrayList<>();
        rents.add(createRentWithPrice(10.0));
        rents.add(createRentWithPrice(20.0));
        when(statisticService.getAllRents()).thenReturn(rents);

        // When
        ResponseEntity<Double> responseEntity = statisticController.getAmountOfAllEarnedMoney();

        // Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(30.0);
    }

    @Test
    public void shouldReturnAmountOfLastMonthEarnedMoney() {
        // Given
        List<Rent> rents = new ArrayList<>();
        rents.add(createRentWithPriceAndStartDate(LocalDate.now().minusMonths(1), 10.0));
        rents.add(createRentWithPriceAndStartDate(LocalDate.now().minusMonths(1), 20.0));
        rents.add(createRentWithPriceAndStartDate(LocalDate.now(), 30.0));
        when(statisticService.getAllRents()).thenReturn(rents);

        // When
        ResponseEntity<Double> responseEntity = statisticController.getAmountOfLastMonthEarnedMoney();

        // Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(30.0);
    }

    @Test
    public void shouldReturnAmountOfLastWeekEarnedMoney() {
        // Given
        List<Rent> rents = new ArrayList<>();
        rents.add(createRentWithPriceAndStartDate(LocalDate.now().minusWeeks(1), 10.0));
        rents.add(createRentWithPriceAndStartDate(LocalDate.now().minusWeeks(1), 20.0));
        rents.add(createRentWithPriceAndStartDate(LocalDate.now(), 30.0));
        when(statisticService.getAllRents()).thenReturn(rents);

        // When
        ResponseEntity<Double> responseEntity = statisticController.getAmountOfLastWeekEarnedMoney();

        // Then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(30.0);
    }

    private Rent createRent(LocalDate startDate) {
        Rent rent = new Rent();
        rent.setStartDate(startDate);
        return rent;
    }

    private Rent createRentWithPrice(double price) {
        Rent rent = new Rent();
        rent.setPrice(price);
        return rent;
    }

    private Rent createRentWithPriceAndStartDate(LocalDate startDate, double price) {
        Rent rent = new Rent();
        rent.setStartDate(startDate);
        rent.setPrice(price);
        return rent;
    }
}
