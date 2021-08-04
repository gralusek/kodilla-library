package com.library.controller;

import com.google.gson.Gson;
import com.library.Dto.BookDto;
import com.library.Dto.ReaderDto;
import com.library.Dto.TitleDto;
import com.library.domain.BookStatus;
import com.library.domain.Reader;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(ReaderController.class)
class ReaderControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReaderController readerController;

    @Test
    void addReader() throws Exception {
        //Given
        ReaderDto readerDto = new ReaderDto();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(readerDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/reader/addReader")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteReader() throws Exception {
        //Given
        ReaderDto readerDto = new ReaderDto(1L, "name", "surname",
                LocalDate.of(2019, 1, 1), new ArrayList<>());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/reader/deleteReader?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getReader() throws Exception {
        ReaderDto readerDto = new ReaderDto(1L, "name", "surname",
                LocalDate.of(2019, 1, 1), new ArrayList<>());
        when(readerController.getReader(readerDto.getId())).thenReturn(readerDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/reader/getReader?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")));
    }

    @Test
    void getReaders() throws Exception {
        //Given
        when(readerController.getReaders()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/reader/getReaders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }
}
