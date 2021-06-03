package com.library;

import com.library.domain.Book;
import com.library.domain.BookStatus;
import com.library.domain.Reader;
import com.library.domain.Title;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRepository;
import com.library.repository.ReaderRepository;
import com.library.repository.TitleRepository;
import com.library.service.BorrowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IntegrationTestSuite {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    BorrowService borrowService;

    @Test
    void integrationTest() {
        //Given
        Reader reader = new Reader();
        Title title = new Title();
        Book book1 = new Book(BookStatus.AVAILABLE, title, new ArrayList<>());
        Book book2 = new Book(BookStatus.AVAILABLE, title, new ArrayList<>());
        Book book3 = new Book(BookStatus.AVAILABLE, title, new ArrayList<>());
        Book book4 = new Book(BookStatus.AVAILABLE, title, new ArrayList<>());
        Book book5 = new Book(BookStatus.AVAILABLE, title, new ArrayList<>());
        //When
        book1.setTitle(title);
        book2.setTitle(title);
        book3.setTitle(title);
        book4.setTitle(title);
        book5.setTitle(title);
        title.getBooks().add(book1);
        title.getBooks().add(book2);
        title.getBooks().add(book3);
        title.getBooks().add(book4);
        title.getBooks().add(book5);
        Reader readerFromDB = readerRepository.save(reader);
        Title titleFromDB = titleRepository.save(title);
        Book book1FromDB = bookRepository.save(book1);
        Book book2FromDB = bookRepository.save(book2);
        Book book3FromDB = bookRepository.save(book3);
        Book book4FromDB = bookRepository.save(book4);
        Book book5FromDB = bookRepository.save(book5);

        //Then
        assertEquals(5,bookRepository.findAll().size());

        //When
        borrowService.borrow(book1FromDB.getId(), readerFromDB.getId());
        borrowService.borrow(book2FromDB.getId(), readerFromDB.getId());
        borrowService.borrow(book3FromDB.getId(), readerFromDB.getId());

        Book book1AfterBorrow = bookRepository.findById(book1FromDB.getId()).get();
        Book book2AfterBorrow = bookRepository.findById(book2FromDB.getId()).get();

        //Then
        assertEquals(BookStatus.BORROWED, book1AfterBorrow.getStatus());
        assertEquals(BookStatus.BORROWED, book2AfterBorrow.getStatus());
        assertEquals(5, bookRepository.findAll().size());

        //When
        borrowService.returnBook(borrowRepository.findAll().get(0).getId());

        Book book1AfterReturn = bookRepository.findById(book1FromDB.getId()).get();

        //Then
        assertEquals(BookStatus.AVAILABLE, book1AfterReturn.getStatus());
        assertEquals(5, bookRepository.findAll().size());


        //CleanUp
        try {
            titleRepository.deleteAll();
            bookRepository.deleteAll();
            readerRepository.deleteAll();
            borrowRepository.deleteAll();
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }
}
