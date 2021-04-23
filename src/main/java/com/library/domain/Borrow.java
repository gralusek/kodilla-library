package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue
    @Column(name = "borrow_id")
    private Long borrowID;

/*    @Column(name = "book_id")
    private String bookID;*/

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "dateOfRent")
    private LocalDate dateOfRent;

    @Column(name = "dateOfReturn")
    private LocalDate dateOfReturn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public Borrow(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
        this.dateOfRent = LocalDate.now();
    }
}
