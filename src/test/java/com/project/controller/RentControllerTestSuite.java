package com.project.controller;

import com.project.domain.Game;
import com.project.domain.Rent;
import com.project.domain.User;
import com.project.dto.RentDto;
import com.project.enums.GameType;
import com.project.exeption.RentNotFoundException;
import com.project.mapper.RentMapper;
import com.project.service.LoggerService;
import com.project.service.RentService;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class RentControllerTestSuite {
    private MockMvc mockMvc;
    @Mock
    private RentService rentService;
    @Mock
    private LoggerService loggerService;
    @Mock
    private RentMapper rentMapper;
    @InjectMocks
    private RentController rentController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(rentController).build();
    }

    @Test
    public void shouldReturnListOfRents() throws Exception {
        // Given
        Rent rent1 = new Rent();
        rent1.setRentId(1L);
        Rent rent2 = new Rent();
        rent2.setRentId(2L);
        List<Rent> rentList = new ArrayList<>();
        rentList.add(rent1);
        rentList.add(rent2);
        RentDto rentDto1 = new RentDto();
        rentDto1.setRentId(1L);
        RentDto rentDto2 = new RentDto();
        rentDto2.setRentId(2L);
        List<RentDto> rentDtoList = new ArrayList<>();
        rentDtoList.add(rentDto1);
        rentDtoList.add(rentDto2);

        when(rentService.getAllRents()).thenReturn(rentList);
        when(rentMapper.mapToRentDtoList(rentList)).thenReturn(rentDtoList);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/rents")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
        assertThat(result.getResponse().getContentAsString()).contains("1", "2");
    }

    @Test
    public void shouldReturnRentById() throws Exception, RentNotFoundException {
        // Given
        Long rentId = 1L;
        Rent rent = new Rent();
        rent.setRentId(rentId);
        RentDto rentDto = new RentDto();
        rentDto.setRentId(rentId);

        when(rentService.getRent(rentId)).thenReturn(rent);
        when(rentMapper.mapToRentDto(rent)).thenReturn(rentDto);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/rents/{rentId}", rentId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
        assertThat(result.getResponse().getContentAsString()).contains("\"rentId\":" + rentId);
    }

    @Test
    public void shouldSaveNewRent() throws Exception {
        //Given
        RentDto rentDto = new RentDto();
        Rent rent = new Rent();

        when(rentMapper.mapToRent(rentDto)).thenReturn(rent);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/rents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isEmpty();
    }

    @Test
    public void shouldUpdateExistingRent() throws Exception {
        // Given
        Game game = new Game(1L, "Kalambury", 10, "2015",
                GameType.FAMILY, "Fajna gra");
        User user = new User(1L, "Ania", "Kania", "kania@ania", "55555");
        RentDto rentDto = new RentDto(1L, user, game, LocalDate.now(), LocalDate.now().plusDays(3), 15);
        Rent rent = new Rent(1L, user, game, LocalDate.now(), LocalDate.now().plusDays(3), 15);

        when(rentMapper.mapToRent(any())).thenReturn(rent);
        when(rentService.saveRent(any())).thenReturn(rent);
        when(rentMapper.mapToRentDto(any())).thenReturn(rentDto);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/rents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isNotEmpty();
    }

    @Test
    public void shouldDeleteExistingRent() throws Exception, RentNotFoundException {
        // Given
        Long rentId = 1L;
        Rent rent = new Rent();

        when(rentService.getRent(rentId)).thenReturn(rent);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/v1/rents/{rentId}", rentId))
                .andReturn();

        // Then
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentAsString()).isEmpty();
    }
}