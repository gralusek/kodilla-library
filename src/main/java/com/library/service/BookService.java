package com.library.service;

import com.library.domain.Book;
import com.library.domain.BookStatus;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public void changeStatus(Long bookId, BookStatus status) {
        Optional<Book> book = bookRepository.findById(bookId);
        book.ifPresent(bookFromDB -> {
            bookFromDB.setStatus(status);
            bookRepository.save(bookFromDB);
        });

    }
}
