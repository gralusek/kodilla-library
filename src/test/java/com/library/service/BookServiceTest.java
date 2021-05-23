package com.library.service;

import com.library.domain.Book;
import com.library.domain.BookStatus;
import com.library.domain.Title;
import com.library.repository.BookRepository;
import com.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Autowired
    TitleRepository titleRepository;

    @Test
    void TestAddBook() {

        //Given
        Book book = new Book();

        //When
        bookRepository.save(book);

        //Then
        assertTrue(bookRepository.existsById(book.getId()));

        //Cleanup
        try {
            bookRepository.deleteById(book.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testChangeStatus() {
        //Given
        Title title = new Title();
        Book book = new Book(BookStatus.AVAILABLE, title, new ArrayList<>());
        //When
        assertEquals(BookStatus.AVAILABLE, book.getStatus());
        titleRepository.save(title);
        bookRepository.save(book);
        bookService.changeStatus(bookRepository.findById(book.getId()).get().getId(), BookStatus.BORROWED);

        //Then
        assertEquals(BookStatus.BORROWED, bookRepository.findById(book.getId()).get().getStatus());

        //CleanUp
        try {
            bookRepository.deleteById(book.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }
}