package com.project.controller;

import com.project.service.DescriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class DescriptionControllerTestSuite {
    private MockMvc mockMvc;
    @Mock
    private DescriptionService descriptionService;

    @BeforeEach
    public void setUp() {
        DescriptionController descriptionController = new DescriptionController(descriptionService);
        mockMvc = MockMvcBuilders.standaloneSetup(descriptionController).build();
    }

    @Test
    public void shouldGetDescription() throws Exception {
        // Given
        String language = "pl";
        String description = "test description";
        String expectedResponse = "Ala ma kota.";

        when(descriptionService.getDescription(language, description)).thenReturn(expectedResponse);

        //When&Then
        mockMvc.perform(get("/v1/description")
                        .param("language", language)
                        .param("description", description)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}

