package com.project.dto;

import com.project.domain.Game;
import com.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class RentDto {
    private Long rentId;
    private User user;
    private Game game;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
}
