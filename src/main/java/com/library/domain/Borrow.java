package com.library.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue
    @Column(name = "borrow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "dateOfRent")
    private LocalDate dateOfRent;

    @Column(name = "dateOfReturn")
    private LocalDate dateOfReturn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public Borrow(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
        this.dateOfRent = LocalDate.now();
        this.dateOfReturn = LocalDate.now().plusDays(30);
    }
}
