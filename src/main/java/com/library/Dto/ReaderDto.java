package com.library.Dto;

import com.library.domain.Borrow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate creationDate;
    private List<BorrowDto> borrowsDtoList;
}
