package com.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Statistics")
public class MonthStatistic {
    private Long statisticId;
    private String month;
    private String year;
    private int numberOfUsers;
    private int numberOfGames;
    private int numberOfAllRents;
    private int numberOfLastMonthRents;
    private double amountOfLastMonthEarnedMoney;

    public MonthStatistic(String month, String year, int numberOfUsers, int numberOfGames, int numberOfAllRents, int numberOfLastMonthRents, double amountOfLastMonthEarnedMoney) {
        this.month = month;
        this.year = year;
        this.numberOfUsers = numberOfUsers;
        this.numberOfGames = numberOfGames;
        this.numberOfAllRents = numberOfAllRents;
        this.numberOfLastMonthRents = numberOfLastMonthRents;
        this.amountOfLastMonthEarnedMoney = amountOfLastMonthEarnedMoney;
    }

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "STATISTIC_ID", unique = true)
    public Long getStatisticId() {
        return statisticId;
    }

    @NonNull
    @Column(name = "MONTH")
    public String getMonth() {
        return month;
    }

    @NonNull
    @Column(name = "YEAR")
    public String getYear() {
        return year;
    }

    @NonNull
    @Column(name = "NUMBER_OF_USERS")
    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    @NonNull
    @Column(name = "NUMBER_OF_GAMES")
    public int getNumberOfGames() {
        return numberOfGames;
    }

    @NonNull
    @Column(name = "NUMBER_OF_ALL_RENTS")
    public int getNumberOfAllRents() {
        return numberOfAllRents;
    }

    @NonNull
    @Column(name = "NUMBER_OF_LAST_MONTH_RENTS")
    public int getNumberOfLastMonthRents() {
        return numberOfLastMonthRents;
    }

    @NonNull
    @Column(name = "NUMBER_OF_LAST_MONTH_ERNED_MONEY")
    public double getAmountOfLastMonthEarnedMoney() {
        return amountOfLastMonthEarnedMoney;
    }
}
