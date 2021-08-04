package com.library.controller;

import com.library.Dto.BookDto;
import com.library.Dto.BorrowDto;
import com.library.Dto.ReaderDto;
import com.library.Dto.TitleDto;
import com.library.domain.BookStatus;
import com.library.domain.Borrow;
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
@WebMvcTest(BorrowController.class)
class BorrowControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BorrowController borrowController;

    @Test
    void borrowBook() throws Exception {
        //Given
        ReaderDto readerDto = new ReaderDto(2L, "name", "surname",
                LocalDate.of(2019, 1, 1), new ArrayList<>());
        BookDto bookDto = new BookDto(1L, BookStatus.AVAILABLE, new TitleDto(), new ArrayList<>());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/borrow/borrowBook?bookId="
                                + bookDto.getId() + "&readerId=" + readerDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void returnBook() throws Exception {
        //Given
        BorrowDto borrowDto = new BorrowDto(1L, new ReaderDto(), LocalDate.of(2021, 1, 1),
                LocalDate.of(2021,4,4), new BookDto());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/borrow/returnBook?borrowId="
                                + borrowDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void getBorrow() throws Exception {
        //Given
        BorrowDto borrowDto = new BorrowDto(1L, new ReaderDto(), LocalDate.of(2021, 1, 1),
                LocalDate.of(2021,4,4), new BookDto());
        when(borrowController.getBorrow(borrowDto.getId())).thenReturn(borrowDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/borrow/getBorrow?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void getBorrows() throws Exception{
        //Given
        when(borrowController.getBorrows()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/borrow/getBorrows")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void deleteById() throws Exception{
        //Given
        BorrowDto borrowDto = new BorrowDto(1L, new ReaderDto(), LocalDate.of(2021, 1, 1),
                LocalDate.of(2021,4,4), new BookDto());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/borrow/deleteById?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}