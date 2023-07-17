package com.project.mapper;

import com.project.domain.Game;
import com.project.dto.GameDto;
import com.project.enums.CurrencyValue;
import com.project.utils.MathUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameMapper {
    public Game mapToGame(final GameDto gameDto) {
        return new Game(
                gameDto.getGameId(),
                gameDto.getTitle(),
                MathUtils.roundToTwoDecimals(gameDto.getPrice() / CurrencyValue.INSTANCE.getValue()),
                gameDto.getPublicationYear(),
                gameDto.getType(),
                gameDto.getDescription()
        );
    }

    public GameDto mapToGameDto(final Game game) {
        return GameDto.builder()
                .gameId(game.getGameId())
                .title(game.getTitle())
                .price(MathUtils.roundToTwoDecimals(game.getPrice() * CurrencyValue.INSTANCE.getValue()))
                .publicationYear(game.getPublicationYear())
                .type(game.getType())
                .description(game.getDescription())
                .build();
    }

    public List<GameDto> mapToGameDtoList(final List<Game> gameList) {
        return gameList.stream()
                .map(this::mapToGameDto)
                .collect(Collectors.toList());
    }
}