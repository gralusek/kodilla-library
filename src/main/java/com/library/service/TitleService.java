package com.library.service;

import com.library.domain.Book;
import com.library.domain.Title;
import com.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;

    public Title addTitle(Title title) {
        return titleRepository.save(title);
    }

    public Optional<Title> findById(Long id) {
        return titleRepository.findById(id);
    }

    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    public void deleteById(Long id) {
        titleRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        titleRepository.deleteByTitle(name);
    }

    public int countBookCopies(Long id) {
        return titleRepository
                .findById(id)
                .map(title -> title.getBooks().size())
                .orElseThrow(()-> new RuntimeException("Title with given id was not found"));
    }


    public List<Title> findByPartOfName(String partOfName) {
        return titleRepository
                .findAll().stream()
                .filter(title -> title.getTitle().contains(partOfName))
                .collect(Collectors.toList());
    }
}
