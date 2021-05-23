package com.library.Dto;

import com.library.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitleDto {
    private Long id;
    private String title;
    private String author;
    private LocalDate yearOfPublication;
    private List<BookDto> booksDtoList;
}
