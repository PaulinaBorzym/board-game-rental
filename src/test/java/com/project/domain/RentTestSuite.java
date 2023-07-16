package com.project.domain;

import com.project.enums.GameType;
import com.project.repository.GameRepository;
import com.project.repository.RentRepository;
import com.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentTestSuite {
    @Autowired
    RentRepository rentRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void shouldCreatedAndDeletedRent() {
        //Given
        User user = new User("Ania", "Kania", "kania@ania", "55555");
        userRepository.save(user);
        Game game = new Game("Kalambury", 10, "2015", GameType.FAMILY, "Fajna gra");
        gameRepository.save(game);
        Rent rent = new Rent(user, game, LocalDate.now(), LocalDate.now().plusDays(3));

        //When
        Long numberOfRents = rentRepository.count();
        rentRepository.save(rent);

        //Then
        assertEquals(numberOfRents + 1, rentRepository.count());
        rentRepository.deleteById(rent.getRentId());
        assertEquals(numberOfRents, rentRepository.count());

        //CleanUp
        gameRepository.deleteById(game.getGameId());
        userRepository.deleteById(user.getUserId());
    }

    @Test
    void shouldFindAllRents() {
        //Given
        Game game = new Game("Kalambury", 10, "2015", GameType.FAMILY, "Fajna gra");
        Game game2 = new Game("Grzybobranie", 10, "2009", GameType.FOR_KIDS, "Zbierasz grzyby");
        gameRepository.save(game);
        gameRepository.save(game2);
        User user = new User("Ania", "Kania", "kania@ania", "55555");
        User user2 = new User("Hania", "Bania", "bania@hania", "465613");
        userRepository.save(user);
        userRepository.save(user2);
        List<Rent> rentList = new ArrayList<>();
        Rent rent = new Rent(user, game, LocalDate.now(), LocalDate.now().plusDays(3));
        Rent rent2 = new Rent(user2, game2, LocalDate.now(), LocalDate.now().plusDays(4));
        rentList.add(rent);
        rentList.add(rent2);

        //When
        Long numberOfRents = rentRepository.count();
        rentRepository.save(rent);
        rentRepository.save(rent2);

        //Then
        assertEquals(numberOfRents + 2, rentRepository.count());

        //CleanUp
        rentRepository.deleteById(rent.getRentId());
        rentRepository.deleteById(rent2.getRentId());
        gameRepository.deleteById(game.getGameId());
        gameRepository.deleteById(game2.getGameId());
        userRepository.deleteById(user.getUserId());
        userRepository.deleteById(user2.getUserId());
    }
}
