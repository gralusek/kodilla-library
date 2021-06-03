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
import java.util.Optional;

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
    void testFindById() {
        //Given
        Title title = new Title();
        Book book = new Book(BookStatus.AVAILABLE, title, new ArrayList<>());

        //When
        titleRepository.save(title);
        bookRepository.save(book);
        Long id = book.getId();
        Optional<Book> book2 = bookRepository.findById(id);

        //Then
        assertEquals(BookStatus.AVAILABLE, book2.get().getStatus());

        //CleanUp
        try {
            titleRepository.deleteById(title.getId());
            bookRepository.deleteById(book.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testFindAll() {
        //Given
        Title title = new Title();
        Book book = new Book(BookStatus.AVAILABLE, title, new ArrayList<>());
        Book book2 = new Book(BookStatus.AVAILABLE, title, new ArrayList<>());

        //When
        titleRepository.save(title);
        bookRepository.save(book);
        bookRepository.save(book2);

        //Then
        assertEquals(2, bookRepository.findAll().size());

        //Cleanup
        try {
            titleRepository.deleteById(title.getId());
            bookRepository.deleteById(book.getId());
            bookRepository.deleteById(book2.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testDeleteById() {
        //Given
        Title title = new Title();
        Book book = new Book(BookStatus.AVAILABLE, title, new ArrayList<>());

        //When
        titleRepository.save(title);
        bookRepository.save(book);
        Long id = book.getId();
        assertTrue(bookRepository.existsById(id));
        bookRepository.deleteById(id);

        //Then
        assertFalse(bookRepository.existsById(id));

        //Cleanup
        try {
            titleRepository.deleteById(title.getId());
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
            titleRepository.deleteById(title.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }
}