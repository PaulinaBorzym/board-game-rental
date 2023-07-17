package com.project.mapper;

import com.project.domain.Game;
import com.project.domain.Rent;
import com.project.domain.User;
import com.project.dto.RentDto;
import com.project.enums.CurrencyValue;
import com.project.enums.GameType;
import com.project.utils.MathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentMapperTestSuite {

    private RentMapper rentMapper = new RentMapper();

    @Test
    public void shouldMapRentDtoToRent() {
        // Given
        User user = new User(1L, "Ania", "Kania", "kania@ania", "55555");
        Game game = new Game(1L, "Kalambury", 10, "2015",
                GameType.FAMILY, "Fajna gra");

        RentDto rentDto = new RentDto(1L, user, game, LocalDate.now(),
                LocalDate.now().plusDays(7), 20);

        // When
        Rent rent = rentMapper.mapToRent(rentDto);

        // Then
        assertEquals(rentDto.getRentId(), rent.getRentId());
        assertEquals(rentDto.getUser(), rent.getUser());
        assertEquals(rentDto.getGame(), rent.getGame());
        assertEquals(rentDto.getStartDate(), rent.getStartDate());
        assertEquals(rentDto.getEndDate(), rent.getEndDate());
        assertEquals(MathUtils.roundToTwoDecimals(rentDto.getPrice() / CurrencyValue.INSTANCE.getValue()),
                rent.getPrice());
    }

    @Test
    public void shouldMapRentToRentDto() {
        // Given
        User user = new User(1L, "Ania", "Kania", "kania@ania", "55555");
        Game game = new Game(1L, "Kalambury", 10, "2015",
                GameType.FAMILY, "Fajna gra");

        Rent rent = new Rent(1L, user, game, LocalDate.now(),
                LocalDate.now().plusDays(7), 20);

        // When
        RentDto rentDto = rentMapper.mapToRentDto(rent);

        // Then
        assertEquals(rent.getRentId(), rentDto.getRentId());
        assertEquals(rent.getUser(), rentDto.getUser());
        assertEquals(rent.getGame(), rentDto.getGame());
        assertEquals(rent.getStartDate(), rentDto.getStartDate());
        assertEquals(rent.getEndDate(), rentDto.getEndDate());
        assertEquals(MathUtils.roundToTwoDecimals(rent.getPrice() * CurrencyValue.INSTANCE.getValue()),
                rentDto.getPrice());
    }

    @Test
    public void shouldMapListOfRentsToListOfRentsDto() {
        // Given
        User user = new User(1L, "Ania", "Kania", "kania@ania", "55555");
        User user2 = new User(2L, "Hnia", "Bania", "hania@bania", "75655");
        Game game = new Game(1L, "Kalambury", 10, "2015",
                GameType.FAMILY, "Fajna gra");
        Game game2 = new Game(2L, "Grzybobranie", 10, "2009",
                GameType.FOR_KIDS, "Zbierasz grzyby");

        Rent rent1 = new Rent(1L, user, game, LocalDate.now(),
                LocalDate.now().plusDays(7), 20);
        Rent rent2 = new Rent(2L, user2, game2, LocalDate.now(),
                LocalDate.now().plusDays(4), 12);

        List<Rent> rentList = Arrays.asList(rent1, rent2);

        // When
        List<RentDto> rentDtoList = rentMapper.mapToRentDtoList(rentList);

        // Then
        assertEquals(rentList.size(), rentDtoList.size());

        for (int i = 0; i < rentList.size(); i++) {
            Rent rent = rentList.get(i);
            RentDto rentDto = rentDtoList.get(i);

            assertEquals(rent.getRentId(), rentDto.getRentId());
            assertEquals(rent.getUser(), rentDto.getUser());
            assertEquals(rent.getGame(), rentDto.getGame());
            assertEquals(rent.getStartDate(), rentDto.getStartDate());
            assertEquals(rent.getEndDate(), rentDto.getEndDate());
            assertEquals(MathUtils.roundToTwoDecimals(rent.getPrice() * CurrencyValue.INSTANCE.getValue()),
                    rentDto.getPrice());
        }
    }
}

