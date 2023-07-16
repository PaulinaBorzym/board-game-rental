package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MonthStatisticDto monthStatisticDto = new MonthStatisticDto();

        public Builder statisticId(Long statisticId) {
            monthStatisticDto.statisticId = statisticId;
            return this;
        }

        public Builder month(String month) {
            monthStatisticDto.month = month;
            return this;
        }
        public Builder year(String year) {
            monthStatisticDto.year = year;
            return this;
        }
        public Builder numberOfUsers(int numberOfUsers) {
            monthStatisticDto.numberOfUsers = numberOfUsers;
            return this;
        }

        public Builder numberOfGames(int numberOfGames) {
            monthStatisticDto.numberOfGames = numberOfGames;
            return this;
        }
        public Builder numberOfAllRents (int numberOfAllRents) {
            monthStatisticDto.numberOfAllRents = numberOfAllRents;
            return this;
        }
        public Builder numberOfLastMonthRents (int numberOfLastMonthRents) {
            monthStatisticDto.numberOfLastMonthRents = numberOfLastMonthRents;
            return this;
        }
        public Builder amountOfLastMonthEarnedMoney (double amountOfLastMonthEarnedMoney) {
            monthStatisticDto.amountOfLastMonthEarnedMoney = amountOfLastMonthEarnedMoney;
            return this;
        }
        public MonthStatisticDto build(){
            return monthStatisticDto ;
        }
    }

}
