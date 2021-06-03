package com.library.service;

import com.library.domain.Reader;
import com.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    @Autowired
    ReaderRepository readerRepository;

    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public Optional<Reader> findById(Long id) {
        return readerRepository.findById(id);
    }

    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    public void deleteById(Long id) {
        readerRepository.deleteById(id);
    }
}
