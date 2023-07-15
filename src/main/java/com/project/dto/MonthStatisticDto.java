package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class MonthStatisticDto {
    private Long statisticId;
    private String month;
    private String year;
    private int numberOfUsers;
    private int numberOfGames;
    private int numberOfAllRents;
    private int numberOfLastMonthRents;
    private double amountOfLastMonthEarnedMoney;


}
