package com.library.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "titles")
public class Title {

    @Id
    @GeneratedValue
    @Column(name = "title_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "yearOfPublication")
    private LocalDate yearOfPublication;

    @OneToMany(
            targetEntity = Book.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Book> books = new ArrayList<>();

    public Title(String title, String author, LocalDate yearOfPublication, List<Book> books) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.books = new ArrayList<>();
    }
}
