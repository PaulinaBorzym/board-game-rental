package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CartDto {
    private long id;
    private BigDecimal totalPrice;
}
