package com.library.service;

import com.library.domain.Book;
import com.library.domain.Title;
import com.library.repository.BookRepository;
import com.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        titleRepository.save(title);

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
        Optional<Title> title2 = titleRepository.findById(title.getId());

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
        assertEquals(2, titleRepository.findAll().size());

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
        titleRepository.deleteById(title.getId());

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
}