package com.library.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @Column(name = "status")
    private BookStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    private Title title;

    @OneToMany(
            targetEntity = Borrow.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Borrow> borrows = new ArrayList<>();

    public Book(BookStatus status, Title title, List<Borrow> borrows) {
        this.status = status;
        this.title = title;
        this.borrows = new ArrayList<>();
    }
}
