package com.library.controller;

import com.library.Dto.BookDto;
import com.library.domain.Book;
import com.library.domain.BookStatus;
import com.library.mapper.BookMapper;
import com.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/book")
public class BookController {
    private final BookMapper bookMapper;
    private final BookService bookService;

    public BookController(BookMapper bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PostMapping(value = "addBook")
    public Book addBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        return bookService.addBook(book);
    }

    @PutMapping(value = "changeStatus")
    public void changeStatus(@RequestParam Long bookId, @RequestParam BookStatus bookStatus) {
        bookService.changeStatus(bookId, bookStatus);
    }

    @DeleteMapping(value = "deleteBook")
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteById(id);
    }

    @GetMapping(value = "getBook")
    public BookDto getBook(@RequestParam Long id) {
        return bookMapper.mapToBookDto(
                bookService.findById(id).orElseThrow(() -> new RuntimeException("Book with given ID was not found")));
    }

    @GetMapping(value = "getBooks")
    public List<BookDto> getBooks() {
        List<Book> books = bookService.findAll();
        return bookMapper.mapToBookDtoList(books);
    }
}
