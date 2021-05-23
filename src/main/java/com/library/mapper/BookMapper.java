package com.library.mapper;

import com.library.Dto.BookDto;
import com.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    @Autowired
    TitleMapper titleMapper;

    @Autowired
    BorrowMapper borrowMapper;

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getStatus(),
                titleMapper.mapToTitle(bookDto.getTitleDto()),
                borrowMapper.mapToBorrowList(bookDto.getBorrowDtoList())
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getId(),
                book.getStatus(),
                titleMapper.mapToTileDto(book.getTitle()),
                borrowMapper.mapToBottowDtoList(book.getBorrows())
        );
    }

    public List<Book> mapToBookList(final List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(this::mapToBook)
                .collect(Collectors.toList());
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}
