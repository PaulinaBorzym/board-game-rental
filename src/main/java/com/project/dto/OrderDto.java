package com.project.dto;

import com.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class OrderDto {
    private Long id;
    private LocalDate orderDate;
    private boolean isPaid;
    private User user;
}
