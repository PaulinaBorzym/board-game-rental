package com.project.mapper;

import com.project.domain.Game;
import com.project.dto.GameDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameMapper {
    public Game mapToGame(final GameDto gameDto) {
        return new Game(
                gameDto.getGameName(),
                gameDto.getDescription(),
                gameDto.getPrice()
        );
    }

    public GameDto mapToGameDto(final Game game){
        return new GameDto(
                game.getGameId(),
                game.getGameName(),
                game.getDescription(),
                game.getPrice()
        );
    }
    public List<GameDto> mapToGameDtoList(final List<Game> gameList){
        return gameList.stream()
                .map(this::mapToGameDto)
                .collect(Collectors.toList());
    }
}
