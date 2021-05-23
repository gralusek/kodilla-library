package com.library.service;

import com.library.domain.Book;
import com.library.domain.BookStatus;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void changeStatus(Long bookId, BookStatus status) {
        Optional<Book> book = bookRepository.findById(bookId);
        book.get().setStatus(status);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}
