package com.library.Dto;

import com.library.domain.BookStatus;
import com.library.domain.Borrow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private BookStatus status;
    private TitleDto titleDto;
    private List<BorrowDto> borrowDtoList;
}
