package com.project.service;

import com.project.domain.Game;
import com.project.exeption.GameNotFoundException;
import com.project.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository repository;

    public List<Game> getAllGames(){
        return repository.findAll();
    }

    public Game getGame(final Long gameId) throws GameNotFoundException {
        return repository.findById(gameId).orElseThrow(GameNotFoundException::new);
    }

    public Game saveGame(final Game game){
        return repository.save(game);
    }

    public void deleteGame(final Long gameId){
        repository.deleteById(gameId);
    }
}
