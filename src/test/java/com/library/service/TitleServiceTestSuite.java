package com.library.service;

import com.library.domain.Book;
import com.library.domain.Title;
import com.library.repository.BookRepository;
import com.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TitleServiceTestSuite {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    TitleService titleService;

    @Test
    void testAddTitle() {
        //Given
        Title title = new Title();

        //When
        titleService.addTitle(title);

        //Then
        assertTrue(titleRepository.existsById(title.getId()));

        //Cleanup
        try {
            titleRepository.deleteById(title.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testFindById() {
        //Given
        Title title = new Title();

        //When
        titleRepository.save(title);
        Optional<Title> title2 = titleService.findById(title.getId());

        //Then
        assertEquals(title.getId(), title2.get().getId());

        //Cleanup
        try {
            titleRepository.deleteById(title.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testFindAll() {
        titleRepository.deleteAll();
        //Given
        Title title = new Title();
        Title title2 = new Title();

        //When
        titleRepository.save(title);
        titleRepository.save(title2);

        //Then
        assertEquals(2, titleService.findAll().size());

        //Cleanup
        try {
            titleRepository.deleteById(title.getId());
            titleRepository.deleteById(title2.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testDeleteById() {
        //Given
        Title title = new Title();

        //When
        titleRepository.save(title);
        assertTrue(titleRepository.existsById(title.getId()));
        titleService.deleteById(title.getId());

        //Then
        assertFalse(titleRepository.existsById(title.getId()));

        //CleanUp
        //No need
    }

    @Test
    public void testCountBookCopies() {

        //Given
        Book book1 = new Book();
        Book book2 = new Book();
        Title title = new Title();

        //When
        book1.setTitle(title);
        book2.setTitle(title);
        title.getBooks().add(book1);
        title.getBooks().add(book2);
        titleRepository.save(title);

        //Then
        assertEquals(2, titleService.countBookCopies(title.getId()));

        //Cleanup
        try {
            titleRepository.deleteById(title.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testFindTitleByPartOfName() {
        //Given
        List<Book> books = new ArrayList<>();
        Title title1 = new Title("Harry Potter and Chamber of Secrets", "J. K. Rowling",
                LocalDate.of(1998, 7, 2), books);
        Title title2 = new Title("Harry Potter and Prinsoner of Azkaban", "J. K. Rowling",
                LocalDate.of(1999, 7, 8), books);
        Title title3 = new Title("Game of thrones", "George R. R. Martin",
                LocalDate.of(1996, 8, 1), books);

        //When
        titleRepository.save(title1);
        titleRepository.save(title2);
        titleRepository.save(title3);

        //Then
        assertEquals(2, titleService.findByPartOfName("Harry").size());

        //Cleanup
        try {
            titleRepository.deleteById(title1.getId());
            titleRepository.deleteById(title2.getId());
            titleRepository.deleteById(title3.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testDeleteTitleByName() {
        titleRepository.deleteAll();
        //Given
        List<Book> books = new ArrayList<>();
        Title title1 = new Title("Harry Potter and Chamber of Secrets", "J. K. Rowling",
                LocalDate.of(1998, 7, 2), books);

        //When
        titleRepository.save(title1);
        assertTrue(titleRepository.existsById(title1.getId()));
        titleService.deleteByName("Harry Potter and Chamber of Secrets");

        //Then
        assertFalse(titleRepository.existsById(title1.getId()));
    }
}