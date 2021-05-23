package com.library.service;

import com.library.domain.Book;
import com.library.domain.Title;
import com.library.repository.BookRepository;
import com.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}