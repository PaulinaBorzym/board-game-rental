package com.project.controller;

import com.project.domain.Game;
import com.project.dto.GameDto;
import com.project.enums.GameType;
import com.project.exeption.GameNotFoundException;
import com.project.mapper.GameMapper;
import com.project.service.GameService;
import com.project.service.LoggerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTestSuite {
    private MockMvc mockMvc;
    @Mock
    private GameService gameService;
    @Mock
    private LoggerService loggerService;
    @Mock
    private GameMapper gameMapper;
    @InjectMocks
    private GameController gameController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    public void shouldReturnListOfGames() throws Exception {
        // Given
        Game game1 = new Game();
        game1.setGameId(1L);
        Game game2 = new Game();
        game2.setGameId(2L);
        List<Game> gameList = new ArrayList<>();
        gameList.add(game1);
        gameList.add(game2);

        GameDto gameDto1 = new GameDto();
        gameDto1.setGameId(1L);
        GameDto gameDto2 = new GameDto();
        gameDto2.setGameId(2L);
        List<GameDto> gameDtoList = new ArrayList<>();
        gameDtoList.add(gameDto1);
        gameDtoList.add(gameDto2);

        when(gameService.getAllGames()).thenReturn(gameList);
        when(gameMapper.mapToGameDtoList(gameList)).thenReturn(gameDtoList);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/games")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
        assertThat(result.getResponse().getContentAsString()).contains("1", "2");
    }

    @Test
    public void shouldReturnGameById() throws Exception, GameNotFoundException {
        // Given
        Long gameId = 1L;
        Game game = new Game();
        game.setGameId(gameId);

        GameDto gameDto = new GameDto();
        gameDto.setGameId(gameId);

        when(gameService.getGame(gameId)).thenReturn(game);
        when(gameMapper.mapToGameDto(game)).thenReturn(gameDto);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/games/{gameId}", gameId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
        assertThat(result.getResponse().getContentAsString()).contains("\"gameId\":" + gameId);
    }

    @Test
    public void shouldSaveNewGame() throws Exception {
        // Given
        GameDto gameDto = new GameDto();
        Game game = new Game();

        when(gameMapper.mapToGame(gameDto)).thenReturn(game);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isEmpty();
    }

    @Test
    public void shouldUpdateExistingGame() throws Exception {
        // Given
        GameDto gameDto = new GameDto(1L, "Kalambury", 10, "2015",
                GameType.FAMILY, "Fajna gra");
        Game game = new Game(1L, "Kalambury", 10, "2015",
                GameType.FAMILY, "Fajna gra");

        when(gameMapper.mapToGame(gameDto)).thenReturn(game);
        when(gameMapper.mapToGameDto(any())).thenReturn(gameDto);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
    }

    @Test
    public void shouldDeleteExistingGame() throws Exception, GameNotFoundException {
        // Given
        Long gameId = 1L;
        Game game = new Game();

        when(gameService.getGame(gameId)).thenReturn(game);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/v1/games/{gameId}", gameId))
                .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isEmpty();
    }
}

