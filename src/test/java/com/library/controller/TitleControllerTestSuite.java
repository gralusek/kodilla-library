package com.library.controller;

import com.google.gson.Gson;
import com.library.Dto.BookDto;
import com.library.Dto.ReaderDto;
import com.library.Dto.TitleDto;
import com.library.domain.BookStatus;
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
@WebMvcTest(TitleController.class)
class TitleControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TitleController titleController;

    @Test
    void addTitle() throws Exception{
        //Given
        TitleDto titleDto = new TitleDto();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(titleDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/title/addTitle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void countBookCopies() throws Exception{
        //Given
        BookDto bookDto = new BookDto(1L, BookStatus.AVAILABLE, new TitleDto(), new ArrayList<>());
        when(titleController.countBookCopies(bookDto.getId())).thenReturn(5);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/title/countBookCopies?bookId=" + bookDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(5)));
    }

    @Test
    void deleteTitleById() throws Exception{
        //Given
        TitleDto titleDto = new TitleDto(1L, "title", "author",
                LocalDate.of(2020,1,1), new ArrayList<>());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/title/deleteTitleById?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteTitleByName() throws Exception{
        //Given
        TitleDto titleDto = new TitleDto(1L, "testTitle", "author",
                LocalDate.of(2020,1,1), new ArrayList<>());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/title/deleteTitleByName?name=testTitle")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getTitle() throws Exception{
        //Given
        TitleDto titleDto = new TitleDto(1L, "testTitle", "author",
                LocalDate.of(2020,1,1), new ArrayList<>());
        when(titleController.getTitle(titleDto.getId())).thenReturn(titleDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/title/getTitle?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("testTitle")));
    }

    @Test
    void getTitles() throws Exception{
        //Given
        when(titleController.getTitles()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/title/getTitles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }
}