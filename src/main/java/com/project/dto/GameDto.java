package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GameDto {
    private Long gameId;
    private String gameName;
    private String description;
    private double price;
}
