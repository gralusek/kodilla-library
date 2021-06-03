package com.library.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "readers")
public class Reader {

    @Id
    @GeneratedValue
    @Column(name = "reader_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "creationDate")
    private LocalDate creationDate;

    @OneToMany(
            targetEntity = Borrow.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Borrow> borrows = new ArrayList<>();

    public Reader(String name, String surname, List<Borrow> borrows) {
        this.name = name;
        this.surname = surname;
        this.creationDate = LocalDate.now();
        this.borrows = borrows;
    }
}
