package com.library.service;

import com.library.domain.Book;
import com.library.domain.Borrow;
import com.library.domain.Reader;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRepository;
import com.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class BorrowService {

    //stworzyć i usunąćw ypożyczenie jak w bookservice i title service

    @Autowired
    private  BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public void borrow(Long bookId, Long readerId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book with given ID was not found"));
        Reader reader = readerRepository
                .findById(readerId)
                .orElseThrow(() -> new RuntimeException("Reader with given ID was not found"));
        Borrow borrow = new Borrow(reader, book);

        borrowRepository.save(borrow);
    }

    public void returnBook(long borrowId) {
        borrowRepository
                .findById(borrowId)
                .map(borrow -> {
                    borrow.setDateOfReturn(LocalDate.now());
                    return borrow;
                })
                .ifPresent(borrow -> borrowRepository.save(borrow));
    }
}
