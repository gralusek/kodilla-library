package com.library.service;

import com.library.domain.Title;
import com.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;


    public int countBookCopies(Long id) {
        return titleRepository
                .findById(id)
                .map(title -> title.getBooks().size())
                .orElseThrow(()-> new RuntimeException("Title with given id was not found"));
    }

    public Title addTitle(Title title) {
        return titleRepository.save(title);
    }
}
