package com.project.controller;

import com.project.domain.MonthStatistic;
import com.project.dto.MonthStatisticDto;
import com.project.mapper.MonthStatisticMapper;
import com.project.service.MonthStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/statistic/month")
public class MonthStatisticController {
    private final MonthStatisticService monthStatisticService;
    private final MonthStatisticMapper monthStatisticMapper;

    @GetMapping
    public ResponseEntity<List<MonthStatisticDto>> getMonthStatistic() {
        List<MonthStatistic> monthStatistic = monthStatisticService.getAllMonthStatistic();
        return ResponseEntity.ok(monthStatisticMapper.mapToMonthStatisticDtoList(monthStatistic));
    }
}
