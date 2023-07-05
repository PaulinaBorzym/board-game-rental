package com.project.repository;

import com.project.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GameRepository extends CrudRepository <Game, Long> {
    @Override
    List<Game> findAll();

    @Override
    Optional<Game> findById(Long gameId);

    @Override
    Game save(Game game);

    @Override
    void deleteById(Long gameId);
}
