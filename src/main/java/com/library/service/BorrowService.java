package com.library.service;

import com.library.domain.Book;
import com.library.domain.BookStatus;
import com.library.domain.Borrow;
import com.library.domain.Reader;
import com.library.repository.BookRepository;
import com.library.repository.BorrowRepository;
import com.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BookService bookService;

    public void borrow(Long bookId, Long readerId) {
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book with given ID was not found"));
        Reader reader = readerRepository
                .findById(readerId)
                .orElseThrow(() -> new RuntimeException("Reader with given ID was not found"));
        bookService.changeStatus(bookId, BookStatus.BORROWED);
        Borrow borrow = new Borrow(reader, book);

        borrowRepository.save(borrow);
    }

    public void returnBook(long borrowId) {
        borrowRepository
                .findById(borrowId)
                .map(borrow -> {
                    borrow.setDateOfReturn(LocalDate.now());
                    borrow.getBook().setStatus(BookStatus.AVAILABLE);
                    return borrow;
                })
                .ifPresent(borrow -> borrowRepository.save(borrow));
    }
}
