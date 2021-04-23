package com.library.service;

import com.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TitleService {

    @Autowired
    private TitleRepository titleRepository;


    public int countBookCopies(Long id) {
        return titleRepository
                .findById(id)
                .map(title -> title.getBooks().size())
                .orElseThrow(()-> new RuntimeException("Title with given id was not found"));
    }
}
