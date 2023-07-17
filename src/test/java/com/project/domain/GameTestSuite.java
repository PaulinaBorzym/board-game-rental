package com.project.domain;

import com.project.enums.GameType;
import com.project.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameTestSuite {

    @Autowired
    GameRepository gameRepository;

    @Test
    void shouldCreateAndDeleteGame() {
        //Given
        Game game = new Game("Kalambury", 10, "2015", GameType.FAMILY, "Fajna gra");

        //When
        Long numberOfGames = gameRepository.count();
        gameRepository.save(game);

        //Then
        assertEquals(numberOfGames + 1, gameRepository.count());
        gameRepository.deleteById(game.getGameId());
        assertEquals(numberOfGames, gameRepository.count());
    }

    @Test
    void shouldFindAllUsers() {
        //Given
        List<Game> gameList = new ArrayList<>();
        Game game = new Game("Kalambury", 10, "2015", GameType.FAMILY, "Fajna gra");
        Game game2 = new Game("Grzybobranie", 10, "2009", GameType.FOR_KIDS,
                "Zbierasz grzyby");
        gameList.add(game);
        gameList.add(game2);

        //When
        Long numberOfGames = gameRepository.count();
        gameRepository.save(game);
        gameRepository.save(game2);

        //Then
        assertEquals(numberOfGames + 2, gameRepository.count());

        //CleanUp
        gameRepository.deleteById(game.getGameId());
        gameRepository.deleteById(game2.getGameId());
    }
}