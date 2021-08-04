package com.library.controller;

import com.google.gson.Gson;
import com.library.Dto.BookDto;
import com.library.Dto.TitleDto;
import com.library.domain.BookStatus;
import com.library.mapper.BookMapper;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(BookController.class)
class BookControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookController bookController;


    @Test
    void addBook() throws Exception{
        //Given
        BookDto bookDto = new BookDto(1L, BookStatus.AVAILABLE, new TitleDto(), new ArrayList<>());
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/book/addBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void changeStatus() {
    }

    @Test
    void deleteBook() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1L, BookStatus.AVAILABLE, new TitleDto(), new ArrayList<>());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/book/deleteBook?id=" + bookDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBook() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1L, BookStatus.AVAILABLE, new TitleDto(), new ArrayList<>());
        when(bookController.getBook(bookDto.getId())).thenReturn(bookDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/book/getBook?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void getBooks() throws Exception {
        //Given
        when(bookController.getBooks()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/book/getBooks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }
}