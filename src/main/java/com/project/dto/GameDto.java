package com.project.dto;

import com.project.enums.GameType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GameDto {
    private Long gameId;
    private String title;
    private double price;
    private String publicationYear;
    private GameType type;
}
