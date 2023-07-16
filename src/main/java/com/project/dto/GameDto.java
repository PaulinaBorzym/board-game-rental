package com.project.dto;

import com.project.enums.GameType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GameDto {
    private Long gameId;
    private String title;
    private double price;
    private String publicationYear;
    private GameType type;
    private String description;

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder {
        private GameDto gameDto = new GameDto();
        public Builder gameId(Long gameId){
            gameDto.gameId = gameId;
            return this;
        }
        public Builder title(String title){
            gameDto.title = title;
            return this;
        }
        public Builder price(double price){
            gameDto.price = price;
            return this;
        }
        public Builder publicationYear(String publicationYear){
            gameDto.publicationYear = publicationYear;
            return this;
        }
        public Builder type(GameType type){
            gameDto.type = type;
            return this;
        }

        public Builder description(String description){
            gameDto.description = description;
            return this;
        }

        public GameDto build(){
            return gameDto;
        }
    }
}
