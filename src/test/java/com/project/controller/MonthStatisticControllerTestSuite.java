package com.project.controller;

import com.project.domain.MonthStatistic;
import com.project.dto.MonthStatisticDto;
import com.project.mapper.MonthStatisticMapper;
import com.project.service.MonthStatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class MonthStatisticControllerTestSuite {
    private MockMvc mockMvc;
    @Mock
    private MonthStatisticService monthStatisticService;
    @Mock
    private MonthStatisticMapper monthStatisticMapper;
    @InjectMocks
    private MonthStatisticController monthStatisticController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(monthStatisticController).build();
    }

    @Test
    public void shouldReturnListOfMonthStatistics() throws Exception {
        // Given
        List<MonthStatistic> monthStatistics = Arrays.asList(new MonthStatistic(), new MonthStatistic());
        List<MonthStatisticDto> monthStatisticsDto = Arrays.asList(new MonthStatisticDto(), new MonthStatisticDto());

        when(monthStatisticService.getAllMonthStatistic()).thenReturn(monthStatistics);
        when(monthStatisticMapper.mapToMonthStatisticDtoList(monthStatistics)).thenReturn(monthStatisticsDto);

        // When&Then
        mockMvc.perform(get("/v1/statistic/month")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(monthStatistics.size()));
    }
}