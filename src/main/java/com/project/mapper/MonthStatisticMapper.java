package com.project.mapper;

import com.project.domain.MonthStatistic;
import com.project.dto.MonthStatisticDto;
import com.project.enums.CurrencyValue;
import com.project.utils.MathUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonthStatisticMapper {
    public MonthStatisticDto mapToMonthStatisticDto(final MonthStatistic monthStatistic) {
        return MonthStatisticDto.builder()
                .statisticId(monthStatistic.getStatisticId())
                .month(monthStatistic.getMonth())
                .year(monthStatistic.getYear())
                .numberOfUsers(monthStatistic.getNumberOfUsers())
                .numberOfGames(monthStatistic.getNumberOfGames())
                .numberOfAllRents(monthStatistic.getNumberOfAllRents())
                .numberOfLastMonthRents(monthStatistic.getNumberOfLastMonthRents())
                .amountOfLastMonthEarnedMoney(MathUtils.roundToTwoDecimals(
                        monthStatistic.getAmountOfLastMonthEarnedMoney() * CurrencyValue.INSTANCE.getValue()))
                .build();
    }

    public List<MonthStatisticDto> mapToMonthStatisticDtoList(final List<MonthStatistic> monthStatisticsList) {
        return monthStatisticsList.stream()
                .map(this::mapToMonthStatisticDto)
                .collect(Collectors.toList());
    }
}