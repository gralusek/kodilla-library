package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

/*    @Column(name = "title_id")
    private String titleId;*/

    @Column(name = "status")
    private String status;

    public void changeStatus(String status) {
        this.status = status;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title_id")
    private Title title;

    @OneToMany(
            targetEntity = Borrow.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Borrow> borrows = new ArrayList<>();
}
