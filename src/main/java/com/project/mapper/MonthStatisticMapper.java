package com.project.mapper;

import com.project.domain.Game;
import com.project.domain.MonthStatistic;
import com.project.dto.GameDto;
import com.project.dto.MonthStatisticDto;
import com.project.enums.CurrencyValue;
import com.project.utils.MathUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonthStatisticMapper {
    public MonthStatisticDto mapToMonthStatisticDto(final MonthStatistic monthStatistic){
        return new MonthStatisticDto(
                monthStatistic.getStatisticId(),
                monthStatistic.getMonth(),
                monthStatistic.getYear(),
                monthStatistic.getNumberOfUsers(),
                monthStatistic.getNumberOfGames(),
                monthStatistic.getNumberOfAllRents(),
                monthStatistic.getNumberOfLastMonthRents(),
                MathUtils.roundToTwoDecimals(monthStatistic.getAmountOfLastMonthEarnedMoney()*CurrencyValue.INSTANCE.getValue())
        );
    }
    public List<MonthStatisticDto> mapToMonthStatisticDtoList(final List<MonthStatistic> monthStatisticsList){
        return monthStatisticsList.stream()
                .map(this::mapToMonthStatisticDto)
                .collect(Collectors.toList());
    }
}
