package com.project.mapper;

import com.project.domain.MonthStatistic;
import com.project.dto.MonthStatisticDto;
import com.project.enums.CurrencyValue;
import com.project.utils.MathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MonthStatisticMapperTestSuite {

    private MonthStatisticMapper monthStatisticMapper = new MonthStatisticMapper();

    @Test
    public void shouldMapMonthStatisticToMonthStatisticDto() {
        // Given

        MonthStatistic monthStatistic = new MonthStatistic(1L, "Jun", "2023", 251,
                60, 150, 67, 3200);

        // When
        MonthStatisticDto monthStatisticDto = monthStatisticMapper.mapToMonthStatisticDto(monthStatistic);

        // Then
        assertEquals(monthStatistic.getStatisticId(), monthStatisticDto.getStatisticId());
        assertEquals(monthStatistic.getMonth(), monthStatisticDto.getMonth());
        assertEquals(monthStatistic.getYear(), monthStatisticDto.getYear());
        assertEquals(monthStatistic.getNumberOfUsers(), monthStatisticDto.getNumberOfUsers());
        assertEquals(monthStatistic.getNumberOfGames(), monthStatisticDto.getNumberOfGames());
        assertEquals(monthStatistic.getNumberOfAllRents(), monthStatisticDto.getNumberOfAllRents());
        assertEquals(monthStatistic.getNumberOfLastMonthRents(), monthStatisticDto.getNumberOfLastMonthRents());
        assertEquals(MathUtils.roundToTwoDecimals(monthStatistic.getAmountOfLastMonthEarnedMoney() *
                CurrencyValue.INSTANCE.getValue()), monthStatisticDto.getAmountOfLastMonthEarnedMoney());
    }

    @Test
    public void shouldMapListOfMonthStatisticsToListOfMonthStatisticsDto() {
        // Given
        MonthStatistic monthStatistic1 = new MonthStatistic(1L, "Jun", "2023", 251,
                60, 150, 67, 3200);
        MonthStatistic monthStatistic2 = new MonthStatistic(2L, "August", "2023", 290,
                80, 190, 87, 4200);

        List<MonthStatistic> monthStatisticsList = Arrays.asList(monthStatistic1, monthStatistic2);

        // When
        List<MonthStatisticDto> monthStatisticDtoList = monthStatisticMapper
                .mapToMonthStatisticDtoList(monthStatisticsList);

        // Then
        assertEquals(monthStatisticsList.size(), monthStatisticDtoList.size());

        for (int i = 0; i < monthStatisticsList.size(); i++) {
            MonthStatistic monthStatistic = monthStatisticsList.get(i);
            MonthStatisticDto monthStatisticDto = monthStatisticDtoList.get(i);

            assertEquals(monthStatistic.getStatisticId(), monthStatisticDto.getStatisticId());
            assertEquals(monthStatistic.getMonth(), monthStatisticDto.getMonth());
            assertEquals(monthStatistic.getYear(), monthStatisticDto.getYear());
            assertEquals(monthStatistic.getNumberOfUsers(), monthStatisticDto.getNumberOfUsers());
            assertEquals(monthStatistic.getNumberOfGames(), monthStatisticDto.getNumberOfGames());
            assertEquals(monthStatistic.getNumberOfAllRents(), monthStatisticDto.getNumberOfAllRents());
            assertEquals(monthStatistic.getNumberOfLastMonthRents(), monthStatisticDto.getNumberOfLastMonthRents());
            assertEquals(MathUtils.roundToTwoDecimals(monthStatistic.getAmountOfLastMonthEarnedMoney() *
                    CurrencyValue.INSTANCE.getValue()), monthStatisticDto.getAmountOfLastMonthEarnedMoney());
        }
    }
}

