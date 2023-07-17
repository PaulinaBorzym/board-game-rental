package com.project.controller;

import com.project.domain.Game;
import com.project.dto.GameDto;
import com.project.enums.LogType;
import com.project.exeption.GameNotFoundException;
import com.project.mapper.GameMapper;
import com.project.service.GameService;
import com.project.service.LoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/games")
public class GameController {
    private final GameService service;
    private final LoggerService logger;
    private final GameMapper gameMapper;

    @GetMapping
    public ResponseEntity<List<GameDto>> getGames() {
        List<Game> games = service.getAllGames();
        return ResponseEntity.ok(gameMapper.mapToGameDtoList(games));
    }

    @GetMapping(value = "{gameId}")
    public ResponseEntity<GameDto> getGame(@PathVariable Long gameId) throws GameNotFoundException {
        return ResponseEntity.ok(gameMapper.mapToGameDto(service.getGame(gameId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGame(@RequestBody GameDto gameDto) {
        Game game = gameMapper.mapToGame(gameDto);
        service.saveGame(game);
        logger.saveGameLog(LogType.CREATE, game);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<GameDto> updateGame(@RequestBody GameDto gameDto) {
        Game game = gameMapper.mapToGame(gameDto);
        logger.saveGameLog(LogType.UPDATE, game);
        Game savedGame = service.saveGame(game);
        return ResponseEntity.ok(gameMapper.mapToGameDto(savedGame));
    }

    @DeleteMapping(value = "{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId) throws GameNotFoundException {
        logger.saveGameLog(LogType.DELETE, service.getGame(gameId));
        service.deleteGame(gameId);
        return ResponseEntity.ok().build();
    }
}
