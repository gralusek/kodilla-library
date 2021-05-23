package com.library.service;

import com.library.domain.Reader;
import com.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {

    @Autowired
    ReaderRepository readerRepository;

    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

}
