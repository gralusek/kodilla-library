package com.library.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDto {
    private Long id;
    private ReaderDto readerDto;
    private LocalDate dateOfRent;
    private LocalDate dateOfReturn;
    private BookDto bookDto;
}
