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

    @Test
    public void testBorrow() {
        //Given
        Book testBook2 = new Book(1L, BookStatus.AVAILABLE, new Title(), new ArrayList<>());
        Book testBook = new Book();
        Reader testReader2 = new Reader(2L, "Name", "Surname", LocalDate.now(), new ArrayList<>());
        Reader testReader = new Reader();
        //Dlaczego w przypadku testów z argumentami, nie przechodzi?

        //When
        bookRepository.save(testBook);
        readerRepository.save(testReader);
        borrowService.borrow(testBook.getId(), testReader.getId());

        //Then
        assertEquals(1, borrowRepository.count());

        //CleanUp
        try {
            bookRepository.deleteById(testBook.getId());
            readerRepository.deleteById(testReader.getId());
            borrowRepository.deleteAll();
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
            //CascadeType=All dlatego nie trzeba usuwać ręcznie testBook i testReader
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }
}