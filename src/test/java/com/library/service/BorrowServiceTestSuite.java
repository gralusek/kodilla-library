package com.library.service;

import com.library.domain.*;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRepository;
import com.library.repository.ReaderRepository;
import com.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BorrowServiceTestSuite {

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BorrowService borrowService;

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    BookService bookService;

    @Test
    void testFindById() {
        //Given
        Book testBook = new Book();
        Reader testReader = new Reader();
        Borrow borrow = new Borrow(testReader, testBook);

        //When
        bookRepository.save(testBook);
        readerRepository.save(testReader);
        borrowRepository.save(borrow);
        Long id = borrow.getId();
        Optional<Borrow> borrow2 = borrowRepository.findById(id);

        //Then
        assertEquals(testBook.getId(), borrow2.get().getBook().getId());

        //Cleanup
        try {
            borrowRepository.deleteById(id);
            bookRepository.deleteById(testBook.getId());
            readerRepository.deleteById(testReader.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testFindAll() {
        //Given
        Book testBook = new Book();
        Reader testReader = new Reader();
        Borrow borrow = new Borrow(testReader, testBook);
        Borrow borrow2 = new Borrow(testReader, testBook);

        //When
        bookRepository.save(testBook);
        readerRepository.save(testReader);
        borrowRepository.save(borrow);
        borrowRepository.save(borrow2);

        //Then
        assertEquals(2, borrowRepository.findAll().size());

        try {
            borrowRepository.deleteById(borrow.getId());
            borrowRepository.deleteById(borrow2.getId());
            bookRepository.deleteById(testBook.getId());
            readerRepository.deleteById(testReader.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testDeleteById() {
        //Given
        Book testBook = new Book();
        Reader testReader = new Reader();
        Borrow borrow = new Borrow(testReader, testBook);

        //When
        bookRepository.save(testBook);
        readerRepository.save(testReader);
        borrowRepository.save(borrow);
        assertTrue(borrowRepository.existsById(borrow.getId()));
        borrowRepository.deleteById(borrow.getId());

        //Then
        assertFalse(borrowRepository.existsById(borrow.getId()));

        //Cleanup
        //No need
    }

    @Test
    public void testBorrow() {
        bookRepository.deleteAll();
        borrowRepository.deleteAll();
        readerRepository.deleteAll();
        //Given
        Title title = new Title();
        Book testBook = new Book(1L, BookStatus.AVAILABLE, title, new ArrayList<>());
        Book testBook2 = new Book();
        Reader testReader = new Reader(2L, "Name", "Surname", LocalDate.now(), new ArrayList<>());
        Reader testReader2 = new Reader();
        //Dlaczego w przypadku testów z argumentami, nie przechodzi?
        //Bo hibernate na własną sekwencję i przy @AutoGenerated id pomija nadane wrtości w argumentach
        // Dlatego w linii 125-126 jest przypisanie do obiektów typu Book i Reader

        //When
        titleRepository.save(title);
        Book book = bookRepository.save(testBook);
        Reader reader = readerRepository.save(testReader);

        borrowService.borrow(book.getId(), reader.getId());
        Book book4 = bookRepository.findById(book.getId()).get();
        //Trzeba jeszcze raz wyciągnąć z bazy danych, ponieważ wyszukanie np
        //book.getStatus() odnosi się do obietku powstałego tutaj, a nie wyciąga z bazy danych
        Borrow borrow = borrowRepository.findAll().get(0);

        //Then
        assertEquals(1, borrowRepository.count());
        assertEquals(1, bookRepository.count());
        assertEquals(BookStatus.BORROWED, book4.getStatus());

        //CleanUp
        try {
            borrowRepository.deleteById(borrow.getId());
            bookRepository.deleteById(book.getId());
            readerRepository.deleteById(reader.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    public void testReturnBook() {
        //Given
        Book testBook = new Book();
        Reader testReader = new Reader();
        Borrow borrow = new Borrow(testReader, testBook);

        //When
        bookRepository.save(testBook);
        readerRepository.save(testReader);
        borrowRepository.save(borrow);
        borrowService.returnBook(borrow.getId());

        //Then
        assertEquals(LocalDate.now(), borrowRepository.findById(borrow.getId()).get().getDateOfReturn());

        //CleanUp
        try {
            borrowRepository.deleteById(borrow.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }
}