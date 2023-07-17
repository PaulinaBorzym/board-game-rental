package com.project.mapper;

import com.project.domain.Game;
import com.project.dto.GameDto;
import com.project.enums.CurrencyValue;
import com.project.enums.GameType;
import com.project.utils.MathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameMapperTestSuite {

    private GameMapper gameMapper = new GameMapper();

    @Test
    public void shouldMapGameDtoToGame() {
        // Given
        GameDto gameDto = new GameDto(1L, "Kalambury", 10, "2015",
                GameType.FAMILY, "Fajna gra");

        // When
        Game game = gameMapper.mapToGame(gameDto);

        // Then
        assertEquals(gameDto.getGameId(), game.getGameId());
        assertEquals(gameDto.getTitle(), game.getTitle());
        assertEquals(MathUtils.roundToTwoDecimals(gameDto.getPrice() / CurrencyValue.INSTANCE.getValue()),
                game.getPrice());
        assertEquals(gameDto.getPublicationYear(), game.getPublicationYear());
        assertEquals(gameDto.getType(), game.getType());
        assertEquals(gameDto.getDescription(), game.getDescription());
    }

    @Test
    public void shouldMapGameToGameDto() {
        // Given
        Game game = new Game(1L, "Kalambury", 10, "2015",
                GameType.FAMILY, "Fajna gra");

        // When
        GameDto gameDto = gameMapper.mapToGameDto(game);

        // Then
        assertEquals(game.getGameId(), gameDto.getGameId());
        assertEquals(game.getTitle(), gameDto.getTitle());
        assertEquals(MathUtils.roundToTwoDecimals(game.getPrice() * CurrencyValue.INSTANCE.getValue()),
                gameDto.getPrice());
        assertEquals(game.getPublicationYear(), gameDto.getPublicationYear());
        assertEquals(game.getType(), gameDto.getType());
        assertEquals(game.getDescription(), gameDto.getDescription());
    }

    @Test
    public void shouldMapListOfGamesToListOfGamesDto() {
        // Given
        Game game1 = new Game(1L, "Kalambury", 10, "2015",
                GameType.FAMILY, "Fajna gra");
        Game game2 = new Game(2L, "Grzybobranie", 10, "2009",
                GameType.FOR_KIDS, "Zbierasz grzyby");

        List<Game> gameList = Arrays.asList(game1, game2);

        // When
        List<GameDto> gameDtoList = gameMapper.mapToGameDtoList(gameList);

        // Then
        assertEquals(gameList.size(), gameDtoList.size());

        for (int i = 0; i < gameList.size(); i++) {
            Game game = gameList.get(i);
            GameDto gameDto = gameDtoList.get(i);

            assertEquals(game.getGameId(), gameDto.getGameId());
            assertEquals(game.getTitle(), gameDto.getTitle());
            assertEquals(MathUtils.roundToTwoDecimals(game.getPrice() * CurrencyValue.INSTANCE.getValue()),
                    gameDto.getPrice());
            assertEquals(game.getPublicationYear(), gameDto.getPublicationYear());
            assertEquals(game.getType(), gameDto.getType());
            assertEquals(game.getDescription(), gameDto.getDescription());
        }
    }
}

